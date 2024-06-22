package com.xux.core.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/22 13:23
 */
@ConfigurationProperties(prefix = "jwt")
@Data
public class JWTProperties {
    @Value("${jwt.sign}")
    private String sign;

    @Value("${jwt.zone-id}")
    private String zoneId;

    @Value("${jwt.expire}")
    private int expire;
}
