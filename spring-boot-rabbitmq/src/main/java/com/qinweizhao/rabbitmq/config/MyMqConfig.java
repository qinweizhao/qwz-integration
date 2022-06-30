package com.qinweizhao.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qinweizhao
 * @since 2022/6/30
 */
@Configuration
public class MyMqConfig {

    public static final String QWZ_EXCHANGE_NAME = "qwz_exchange";
    public static final String QWZ_QUEUE_NAME = "qwz_queue";
    public static final String QWZ_ROUTING_KEY = "qwz.send.msg";
    @Bean
    Queue queue() {
        return new Queue(QWZ_QUEUE_NAME, true, false, false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(QWZ_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding binding() {
        return new Binding(QWZ_QUEUE_NAME,
                Binding.DestinationType.QUEUE,
                QWZ_EXCHANGE_NAME,
                QWZ_ROUTING_KEY,
                null);
    }

}
