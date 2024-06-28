package com.xux.order.rabbitmq;

import com.xux.order.service.OrderService;
import com.xux.rabbitmq.annotation.Idempotent;
import com.xux.rabbitmq.entity.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.xux.rabbitmq.constant.MQConstant.*;

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
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = CREATE_ORDER_QUEUE_NAME)
//    @Idempotent
    public void createOrder(OrderMessage message){
        log.info("处理创建订单:{}", message);
        Integer orderId = orderService.createOrderByMessage(message);
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, ORDER_ROUTE_KEY, orderId);
    }

    @RabbitListener(queues = TIMEOUT_ORDER_QUEUE_NAME)
    public void timeoutOrder(Integer orderId){
        log.info("订单超时:{}", orderId);
        orderService.orderTimeout(orderId);
    }
}
