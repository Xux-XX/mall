package com.xux.seckill.service.impl;

import com.alibaba.fastjson2.JSON;
import com.xux.commonWeb.context.UserContext;
import com.xux.seckill.pojo.entity.SeckillMessage;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.pojo.constant.RedisConstant;
import com.xux.seckill.service.SeckillService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SeckillServiceImpl implements SeckillService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedissonClient redissonClient;
    private final int USER_WAIT_TIME = 3;
    private final int PRODUCT_WAIT_TIME = 3;
    private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private final RabbitTemplate rabbitTemplate;
    private final String EXCHANGE_NAME = "mall_exchange";
    private final String ROUTE_KEY = "seckill";

    /**
     *
     * 1. 当秒杀商品不在缓存内，秒杀已经结束或还未开始,返回OUT_OF_TIME
     *    只通过前端访问的话是这个条件是不会成立的,只有秒杀开始才能点击按钮
     * 2. 判断是否处于秒杀时间段,判断失败返回OUT_OF_TIME
     * 3. 对redis中user的key进行加锁,判断用户购买数量是否超过限制,超过限制返回LIMIT
     * 4. 对商品key加锁,尝试扣减库存,扣减成功则将信息发送到消息队列异步创建订单
     * @param seckillId 秒杀场次id
     * @param productId 秒杀商品id
     * @param number 秒杀数量
     * @return SeckillEnum 操作状态
     */
    @SneakyThrows
    @Override
    @Transactional
    public SeckillEnum doSeckill(Integer seckillId, Integer productId, Integer number){
        // 1. 当秒杀商品不在缓存内，秒杀已经结束或还未开始,返回OUT_OF_TIME
        //   只通过前端访问的话是这个条件是不会成立的,只有秒杀开始才能点击按钮
        String seckillKey = RedisConstant.getSeckillKey(seckillId);
        if (Boolean.FALSE.equals(redisTemplate.hasKey(seckillKey) ) ) return SeckillEnum.OUT_OF_TIME;

        // 2. 判断是否处于秒杀时间段,判断失败返回OUT_OF_TIME
        Map<Object, Object> time = redisTemplate.opsForHash().entries(RedisConstant.getSeckillKey(seckillId));
        Instant begin = Instant.ofEpochMilli((Long) time.get(RedisConstant.START_TIME));
        Instant end = Instant.ofEpochMilli((Long) time.get(RedisConstant.END_TIME));
        Instant now = Instant.now();
        if (!now.isBefore(end) || !now.isAfter(begin)) return SeckillEnum.OUT_OF_TIME;

        // 3. 对redis中user的key进行加锁,判断用户购买数量是否超过限制,超过限制返回LIMIT
        String userKey = RedisConstant.getUserKey(seckillId, productId, UserContext.get().getUserId());
        String productKey = RedisConstant.getProductKey(seckillId, productId);
        Integer limit = (Integer) redisTemplate.opsForValue().get(RedisConstant.getLimitKey(productKey));
        RLock userLock = redissonClient.getLock(RedisConstant.getUserLockKey(userKey));
        boolean isLock = userLock.tryLock(USER_WAIT_TIME, TIME_UNIT);
        if (!isLock) return SeckillEnum.RETRY;
        try {
            Object count = redisTemplate.opsForValue().get(userKey);
            Integer hasBuyCount = (Integer) (count == null ? 0 : count);
            if (hasBuyCount + number > limit){
                redisTemplate.expireAt(userKey, end.plusMillis(3));
                return SeckillEnum.LIMIT;
            }
        }finally {
            userLock.unlock();
        }

        // 4. 对商品库存加锁,尝试扣减库存,扣减成功则将信息发送到消息队列异步创建订单
        //    若库存不足则返回SOLD_OUT
        String stockKey = RedisConstant.getStockKey(productKey);
        RLock productLock = redissonClient.getLock(RedisConstant.getStockLockKey(stockKey));
        isLock = productLock.tryLock(PRODUCT_WAIT_TIME, TIME_UNIT);
        if (!isLock) return SeckillEnum.RETRY;
        try {
            Integer stock = (Integer) redisTemplate.opsForValue().get(stockKey);
            if (stock <= 0) return SeckillEnum.SOLD_OUT;
            redisTemplate.opsForValue().decrement(stockKey, number);
            redisTemplate.opsForValue().increment(userKey, number);
            SeckillMessage message = SeckillMessage.builder().
                    userId(UserContext.get().getUserId()).
                    productId(productId)
                    .build();
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, JSON.toJSON(message));

        }finally {
            productLock.unlock();
        }
        log.info("用户{}抢购成功", UserContext.get());
        return SeckillEnum.SUCCESS;
    }

}
