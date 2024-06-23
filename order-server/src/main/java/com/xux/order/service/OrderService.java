package com.xux.order.service;

import com.xux.order.pojo.dto.CreateOrderDto;
import com.xux.order.pojo.enums.CreateOrderEnum;
import com.xux.rabbitmq.entity.OrderMessage;


/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 17:11
 */
public interface OrderService {
    /**
     * 通过消息队列的消息创建订单
     * @param message 发送过来的消息
     * @return CreateOrderEnum
     */
    CreateOrderEnum createOrderByMessage(OrderMessage message);

    /**
     * 使用多个商品创建订单
     * @param createOrderDto 创建订单所需信息
     * @return CreateOrderEnum
     */
    CreateOrderEnum createByProductList(CreateOrderDto createOrderDto);
}
