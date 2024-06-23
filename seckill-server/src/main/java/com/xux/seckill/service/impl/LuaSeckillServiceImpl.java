package com.xux.seckill.service.impl;

import com.xux.commonWeb.context.UserContext;
import com.xux.feign.api.AddressFeignClient;
import com.xux.rabbitmq.entity.OrderMessage;
import com.xux.rabbitmq.util.MessageUtil;
import com.xux.seckill.pojo.constant.RedisConstant;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.service.SeckillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/22 19:31
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LuaSeckillServiceImpl implements SeckillService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate rabbitTemplate;
    private final AddressFeignClient addressFeignClient;
    private final String EXCHANGE_NAME = "mall_exchange";
    private final String ROUTE_KEY = "seckill.order.create";
    private final String SECKILL_SCRIPT =
        """
        --判断是否处于秒杀时间段
        local seckillKey = KEYS[1]
        if not redis.call('EXISTS', seckillKey) then
            return 1
        end
        local nowTime = tonumber(ARGV[1])
        local beginTime = tonumber(redis.call('HGET', seckillKey, 'startTime'))
        local endTime = tonumber(redis.call('HGET', seckillKey, 'endTime'))
        if nowTime < beginTime or nowTime > endTime then
            return 1
        end
        --判断是否到达购买上限
        local userKey = KEYS[2]
        local limitKey = KEYS[3]
        local maxCount = tonumber(redis.call('GET', limitKey))
        local buyCount = tonumber(ARGV[2])
        local currentCount = tonumber(redis.call('GET', userKey) or '0')
        if currentCount + buyCount > maxCount then
            return 2
        end
        --判断库存是否足够
        local stockKey = KEYS[4]
        local stock = tonumber(redis.call('GET', stockKey) or '0')
        if stock < buyCount then
            return 3
        end
        --更新库存
        redis.call('INCRBY', userKey, buyCount)
        redis.call('DECRBY', stockKey, buyCount)
        
        return 0
        """;


    /**
     * 使用lua脚本完成秒杀流程，
     * 秒杀成功将信息发送给mq进行后续处理
     * 如：创建订单等操作
     */
    @Override
    public SeckillEnum doSeckill(Integer seckillId, Integer productId, Integer number, Integer addressId) {
        // 校验参数是否合法
        Boolean isExists = addressFeignClient.exists(addressId);
        if (!isExists) return SeckillEnum.ERROR_ARG;

        // 准备好需要的参数
        String seckillKey = RedisConstant.getSeckillKey(seckillId);
        String productKey = RedisConstant.getProductKey(seckillId, productId);
        String stockKey = RedisConstant.getStockKey(productKey);
        String userKey = RedisConstant.getUserKey(productKey, UserContext.get().getUserId());
        String limitKey = RedisConstant.getLimitKey(productKey);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(SECKILL_SCRIPT, Long.class);
        List<String> keys = List.of(seckillKey, userKey, limitKey, stockKey);

        // 使用lua脚本完成秒杀流程
        Long index = redisTemplate.execute(redisScript, keys, Instant.now().toEpochMilli(), number);
        SeckillEnum status = SeckillEnum.values()[Math.toIntExact(index)];
        if (status == SeckillEnum.SUCCESS){
            log.info("用户{}抢购成功:商品id为{}", UserContext.get(), productId);
            OrderMessage message = new OrderMessage();
            message.setMessageId(MessageUtil.randomMessageId());
            message.setNumber(number);
            message.setUserId(UserContext.get().getUserId());
            message.setAddressId(addressId);
            message.setProductId(productId);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, message);
        }
        return status;
    }
}
