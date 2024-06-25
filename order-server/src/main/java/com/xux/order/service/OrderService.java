package com.xux.order.service;

import com.xux.order.pojo.dto.CreateOrderDto;
import com.xux.order.pojo.vo.OrderVo;
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
     */
    void createOrderByMessage(OrderMessage message);

    /**
     * 使用多个商品创建订单
     * @param createOrderDto 创建订单所需信息
     * @return Integer 订单id
     */
    Integer createByProductList(CreateOrderDto createOrderDto);

    /**
     * 通过秒杀messageId和用户id查询处于待支付的订单
     * 用于秒杀场景下前端轮询订单是否创建成功
     * @param seckillMessageId 秒杀消息id
     * @return OrderVo
     */
    OrderVo getBySeckillMessageId(Long seckillMessageId);
}
