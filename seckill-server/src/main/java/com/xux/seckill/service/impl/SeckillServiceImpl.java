package com.xux.seckill.service.impl;

import com.xux.seckill.feign.OrderFeignClient;
import com.xux.seckill.pojo.enums.SeckillEnum;
import com.xux.seckill.service.SeckillService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

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

        return null;
    }
}
