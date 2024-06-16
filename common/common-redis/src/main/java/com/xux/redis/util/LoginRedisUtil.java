package com.xux.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 22:18
 */
public class LoginRedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${spring.data.redis.default-expire}")
    private Integer expire;

    private final String prefix = "user:";

    public void set(String key, String value){
        redisTemplate.opsForValue().set(prefix + key, value, expire, TimeUnit.HOURS);
    }

    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void remove(String key){
        redisTemplate.delete(key);
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(prefix + key);
    }
}
