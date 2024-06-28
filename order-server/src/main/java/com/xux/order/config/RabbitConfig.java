package com.xux.order.config;

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
                Map.of(MQConstant.DELAY_TYPE_ARG_KEY, MQConstant.TOPIC)
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
    @Bean
    Queue createOrderQueue(){
        return new Queue(
                MQConstant.CREATE_ORDER_QUEUE_NAME,
                true,
                false,
                false,
                Map.of(
                        MQConstant.FAIL_EXCHANGE_ARG_KEY, MQConstant.FAIL_EXCHANGE_NAME,
                        MQConstant.FAIL_ROUTE_ARG_KEY, MQConstant.MESSAGE_PROCESS_FAIL_ROUTE_KEY
                )
        );
    }

    @Bean
    Binding bindCreateQueue(Exchange mallExchange, Queue createOrderQueue){
        return BindingBuilder
                .bind(createOrderQueue)
                .to(mallExchange)
                .with(MQConstant.CREATE_ORDER_BIND_KEY)
                .noargs();
    }
}
