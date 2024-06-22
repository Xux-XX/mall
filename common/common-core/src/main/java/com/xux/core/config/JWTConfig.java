package com.xux.core.config;

import com.xux.core.property.JWTProperties;
import com.xux.core.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/16 21:25
 */
@EnableConfigurationProperties(JWTProperties.class)
@ConditionalOnProperty("jwt.enable")
public class JWTConfig {
    @Bean
    public JWTUtil jwtUtil(JWTProperties properties){
        return new JWTUtil(properties.getSign(), properties.getExpire(), properties.getZoneId());
    }
}
