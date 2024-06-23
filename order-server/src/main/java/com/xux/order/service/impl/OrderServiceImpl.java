package com.xux.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.order.mapper.OrderMapper;
import com.xux.order.pojo.dto.CreateOrderDto;
import com.xux.order.pojo.entity.Order;
import com.xux.order.pojo.enums.CreateOrderEnum;
import com.xux.order.service.OrderService;
import com.xux.rabbitmq.entity.OrderMessage;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 20:37
 */
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
    @Override
    public CreateOrderEnum createOrderByMessage(OrderMessage message) {
        return null;
    }

    @Override
    public CreateOrderEnum createByProductList(CreateOrderDto createOrderDto) {

        return null;
    }
}
