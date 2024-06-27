package com.xux.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/25 18:22
 */
@ConfigurationProperties(prefix = "spring.data.redis.bloom-filter")
@Data
public class BloomFilterProperties {
    private Integer expectedInsertions;
    private Double falseProbability;
}
