package com.xux.seckill.pojo.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/21 23:17
 */
@Data
@Builder
public class SeckillMessage {
    private Integer userId;
    private Integer productId;
    private Integer number;
}
