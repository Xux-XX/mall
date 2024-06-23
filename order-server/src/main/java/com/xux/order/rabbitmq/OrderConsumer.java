package com.xux.order.rabbitmq;

import com.xux.rabbitmq.entity.OrderMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 15:17
 */
@Component
public class OrderConsumer {
    private final String QUEUE_NAME = "create_order";

    @RabbitListener(queues = QUEUE_NAME)
    public void createOrder(OrderMessage message){
        System.out.println("接收到消息" + message);
    }
}
