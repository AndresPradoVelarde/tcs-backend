package com.tcs.accountservice.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE    = "tcs.cliente.exchange";
    public static final String QUEUE       = "tcs.cliente.queue";
    public static final String ROUTING_KEY = "cliente.#";

    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue clienteQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding clienteBinding(Queue clienteQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(clienteQueue).to(clienteExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
