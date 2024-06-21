package com.xux.core.config;

import com.xux.core.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/16 21:25
 */

public class JWTConfig {
    @Bean
    @ConditionalOnProperty(name = "jwt")
    public JWTUtil jwtUtil(@Value("${jwt.sign}")String sign,
                           @Value("${jwt.zone-id}")String zoneId,
                           @Value("${jwt.expire}")int expire){
        return new JWTUtil(sign, expire, zoneId);
    }
}
