package com.xux.seckill.service.impl;

import com.xux.common.context.UserContext;
import com.xux.common.entity.UserInfo;
import com.xux.seckill.feign.OrderFeignClient;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.service.SeckillService;
import com.xux.seckill.util.SeckillUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SeckillServiceImpl implements SeckillService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final OrderFeignClient orderClient;
    private final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RedissonClient redissonClient;

    @Override
    @Transactional
    public SeckillEnum doSeckill(Integer seckillId, Integer productId) {
//        String key = SeckillUtil.getProductKey(seckillId, productId);
//        // 判断秒杀是否开始或者结束
//        boolean isEnded = Boolean.FALSE.equals(redisTemplate.hasKey(key));
//        if (isEnded) return SeckillEnum.ENDED;
//        LocalDateTime start = LocalDateTime.parse((String)redisTemplate.opsForHash().get(key, "start"), DATETIME_FORMAT);
//        boolean isStart = LocalDateTime.now().isAfter(start);
//        if(!isStart) return SeckillEnum.NOT_START;
//
//        // 对这个商品加锁
//        Long total1 = redisTemplate.opsForHash().increment(key, "total", -1);
//        // 判断此用户是否已经达到购买上限
//        UserInfo userInfo = UserContext.get();
//        String userKey = SeckillUtil.getUserKey(userInfo.getUserId(), seckillId, productId);
//        Long buyCount = redisTemplate.opsForHash().increment(userKey, "buyCount", 1);
//        redisTemplate.expire(userKey, redisTemplate.getExpire(key, TimeUnit.SECONDS), TimeUnit.SECONDS);
//        Integer limit = (Integer) redisTemplate.opsForHash().get(key, "limit");
//        if (buyCount > limit) return SeckillEnum.LIMIT;
//
//        // 扣减库存并创建订单
//        Long total = redisTemplate.opsForHash().increment(key, "total", -1);
//        if (total < 0) return SeckillEnum.SOLD_OUT;
////        Result result = orderClient.createSeckillOrder(seckillId, productId, userInfo.getJwt());
////        if (result.getCode() != 200) return SeckillEnum.ERROR;
//        return SeckillEnum.SUCCESS;
        String key = SeckillUtil.getProductKey(seckillId, productId);
        Map<Object, Object> product = redisTemplate.opsForHash().entries(key);
        if (product.size() == 0) return SeckillEnum.ENDED;
        return null;
    }
}
