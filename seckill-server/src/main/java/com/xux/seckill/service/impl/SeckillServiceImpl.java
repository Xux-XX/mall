package com.xux.seckill.service.impl;

import com.xux.commonWeb.context.UserContext;
import com.xux.seckill.pojo.entity.SeckillMessage;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.pojo.constant.RedisConstant;
import com.xux.seckill.service.SeckillService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:22
 */
@Service
@RequiredArgsConstructor
public class SeckillServiceImpl implements SeckillService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedissonClient redissonClient;
    private final int USER_WAIT_TIME = 3;
    private final int PRODUCT_WAIT_TIME = 3;
    private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private final RabbitTemplate rabbitTemplate;
    private final String ROUTE_KEY = "routeKey";

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
    @Override
    public SeckillEnum doSeckill(Integer seckillId, Integer productId, Integer number) throws InterruptedException {
        // 1. 当秒杀商品不在缓存内，秒杀已经结束或还未开始,返回OUT_OF_TIME
        //   只通过前端访问的话是这个条件是不会成立的,只有秒杀开始才能点击按钮
        if (Boolean.FALSE.equals(redisTemplate.hasKey(String.valueOf(seckillId)))) return SeckillEnum.OUT_OF_TIME;
        // 2. 判断是否处于秒杀时间段,判断失败返回OUT_OF_TIME
        if (!isOnTime(seckillId)) return SeckillEnum.OUT_OF_TIME;
        // 3. 对redis中user的key进行加锁,判断用户购买数量是否超过限制,超过限制返回LIMIT
        String userKey = RedisConstant.getUserKey(seckillId, UserContext.get().getUserId());
        String productKey = RedisConstant.getProductKey(seckillId, productId);
        Integer limit = (Integer) redisTemplate.opsForValue().get(RedisConstant.getLimitKey(productKey));
        RLock userLock = redissonClient.getLock(userKey);
        boolean isLock = userLock.tryLock(USER_WAIT_TIME, TIME_UNIT);
        if (!isLock) return SeckillEnum.RETRY;
        try {
            Long increment = redisTemplate.opsForValue().increment(userKey, number);
            if (increment > limit){
                redisTemplate.opsForValue().decrement(userKey, number);
                return SeckillEnum.LIMIT;
            }
        }finally {
            userLock.unlock();
        }
        // 4. 对商品key加锁,尝试扣减库存,扣减成功则将信息发送到消息队列异步创建订单
        RLock productLock = redissonClient.getLock(productKey);
        isLock = productLock.tryLock(PRODUCT_WAIT_TIME, TIME_UNIT);
        if (!isLock) return SeckillEnum.RETRY;
        try {
            String stockKey = RedisConstant.getStockKey(productKey);
            Integer stock = (Integer) redisTemplate.opsForValue().get(stockKey);
            if (stock > 0) redisTemplate.opsForValue().decrement(stockKey);
            SeckillMessage message = SeckillMessage.builder().
                    userId(UserContext.get().getUserId()).
                    productId(productId)
                    .build();
            rabbitTemplate.convertAndSend(ROUTE_KEY, message);
        }finally {
            productLock.unlock();
        }
        return SeckillEnum.SUCCESS;
    }

    /**
     * 通过Redis判断是否处于秒杀时间段
     * @param seckillId 秒杀场次id
     * @return boolean
     */
    private boolean isOnTime(Integer seckillId){
        Map<Object, Object> time = redisTemplate.opsForHash().entries(RedisConstant.getSeckillKey(seckillId));
        Instant begin = (Instant) time.get(RedisConstant.START_TIME);
        Instant end = (Instant) time.get(RedisConstant.END_TIME);
        Instant now = Instant.now();
        return now.isBefore(end) && now.isAfter(begin);
    }
}
