package com.tcs.clientservice.infrastructure.adapter.out.messaging;

import com.tcs.clientservice.infrastructure.adapter.out.messaging.event.ClienteEvent;
import com.tcs.clientservice.infrastructure.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publicar(ClienteEvent evento) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "cliente.eventos", evento);
    }
}
