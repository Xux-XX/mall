package com.xux.rabbitmq.config;

import com.xux.rabbitmq.constant.MQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 16:31
 */
@ComponentScan("com.xux.rabbitmq.aspect")
public class RabbitConfig {
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    DirectExchange failExchange(){
        return new DirectExchange(MQConstant.FAIL_EXCHANGE_NAME);
    }
    @Bean
    Queue failQueue(){
        return new Queue(MQConstant.FAIL_QUEUE_NAME);
    }
    @Bean
    Binding failMessageBinding(Exchange failExchange, Queue failQueue){
        return BindingBuilder
                .bind(failQueue)
                .to(failExchange)
                .with(MQConstant.MESSAGE_PROCESS_FAIL_ROUTE_KEY)
                .noargs();
    }
    @Bean
    TopicExchange mallExchange(){
        return new TopicExchange(MQConstant.BUSINESS_EXCHANGE_NAME);
    }

}
