package com.xux.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于通过消息队列创建订单时的传输
 * @author xux
 * @version 0.1
 * @since 2024/6/23 15:20
 */
@Data
public class OrderMessage {
    /**
     * 消息id，用于保证幂等性
     */
    private Long messageId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 购买数量
     */
    private Integer number;
    /**
     * 地址id
     */
    private Integer addressId;
}
