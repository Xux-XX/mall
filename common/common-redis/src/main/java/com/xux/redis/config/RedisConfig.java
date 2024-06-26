package com.xux.redis.config;

import com.xux.redis.properties.BloomFilterProperties;
import com.xux.redis.util.LoginRedisUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@EnableConfigurationProperties(BloomFilterProperties.class)
public class RedisConfig implements CachingConfigurer {
    @Bean
    public RedisTemplate<String, Object> stringObjectRedisTemplate(RedisConnectionFactory  redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new FastJson2JsonRedisSerializer<>(Object.class));
        return redisTemplate;
    }
    @Bean
    public LoginRedisUtil redisUtil(){
        return new LoginRedisUtil();
    }
}
