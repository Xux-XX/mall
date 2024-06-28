package com.xux.redis.aspect;

import com.xux.redis.annotation.BloomFilter;
import com.xux.redis.properties.BloomFilterProperties;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/25 18:18
 */
@Aspect
@Component
@RequiredArgsConstructor
public class BloomFilterAspect {
    private final RedissonClient redissonClient;
    private final BloomFilterProperties properties;

    @Around("@annotation(bloomFilter)")
    public Object filterAndAdd(ProceedingJoinPoint joinPoint, BloomFilter bloomFilter) throws Throwable {
        RBloomFilter<Object> bf = redissonClient.getBloomFilter(bloomFilter.value());
        bf.tryInit(properties.getExpectedInsertions(), properties.getFalseProbability());
        Object arg = joinPoint.getArgs()[bloomFilter.argId()];
        // 当布隆过滤器判断为空时,表示这个值不存在直接返回
        // 当查询结果不为空时,将arg加入布隆过滤器，避免一开始就初始化
        if (!bf.contains(arg))return null;
        Object res = joinPoint.proceed();
        if (res != null)bf.add(arg);
        return res;
    }
}
