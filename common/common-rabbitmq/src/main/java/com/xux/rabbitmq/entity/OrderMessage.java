package com.xux.rabbitmq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用于通过消息队列创建订单时的传输
 * @author xux
 * @version 0.1
 * @since 2024/6/23 15:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderMessage extends Message{
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
     * 总共需支付价格
     */
    private Double totalPrice;
    /**
     * 地址id
     */
    private Integer addressId;
}
