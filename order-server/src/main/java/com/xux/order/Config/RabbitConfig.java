package com.xux.order.Config;

import com.xux.rabbitmq.constant.MQConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/28 12:27
 */
@Configuration
public class RabbitConfig {

    @Bean
    public CustomExchange delayExchange(){
        return new CustomExchange(
                MQConstant.DELAY_EXCHANGE_NAME,
                MQConstant.DELAY_EXCHANGE_TYPE,
                true,
                false,
                Map.of(MQConstant.DELAY_TYPE_KEY, MQConstant.TOPIC)
        );
    }
    @Bean
    public Queue timeOutQueue(){
        return new Queue(MQConstant.TIMEOUT_ORDER_QUEUE_NAME);
    }
    @Bean
    public Binding timeOutbinding(CustomExchange delayExchange, Queue timeOutQueue){
        return BindingBuilder
                .bind(timeOutQueue)
                .to(delayExchange)
                .with(MQConstant.ORDER_ROUTE_KEY)
                .noargs();
    }
}
