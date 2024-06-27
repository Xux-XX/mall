package com.xux.order.rabbitmq;

import com.xux.order.service.OrderService;
import com.xux.rabbitmq.annotation.Idempotent;
import com.xux.rabbitmq.entity.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 15:17
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {
    private final OrderService orderService;
    private final String QUEUE_NAME = "create_order";


    @RabbitListener(queues = QUEUE_NAME)
    @Idempotent
    public void createOrder(OrderMessage message){
        log.info("处理创建订单消息:{}", message);
        orderService.createOrderByMessage(message);
    }
}