package com.xux.order;

import com.xux.rabbitmq.entity.OrderMessage;
import com.xux.rabbitmq.util.MessageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.RabbitUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/25 17:16
 */
@SpringBootTest
public class OrderTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    private final String EXCHANGE_NAME = "mall_exchange";
    private final String ROUTE_KEY = "seckill.order.create";
    @Test
    void testConsumer(){
        OrderMessage message = new OrderMessage();
        message.setMessageId(MessageUtil.snowflakeId());
        message.setUserId(1);
        message.setNumber(1);
        message.setProductId(1);
        message.setAddressId(1);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, message);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, message);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTE_KEY, message);
    }
}
