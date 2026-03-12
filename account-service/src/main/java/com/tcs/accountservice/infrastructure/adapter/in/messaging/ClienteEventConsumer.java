package com.tcs.accountservice.infrastructure.adapter.in.messaging;

import com.tcs.accountservice.infrastructure.adapter.in.messaging.event.ClienteEvent;
import com.tcs.accountservice.infrastructure.adapter.out.persistence.ClienteLocalJpaRepository;
import com.tcs.accountservice.infrastructure.adapter.out.persistence.entity.ClienteLocalEntity;
import com.tcs.accountservice.infrastructure.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteEventConsumer {

    private final ClienteLocalJpaRepository clienteLocalJpaRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumir(ClienteEvent evento) {
        switch (evento.getTipoEvento()) {
            case "CREADO", "ACTUALIZADO" -> {
                ClienteLocalEntity entity = clienteLocalJpaRepository.findById(evento.getId())
                        .orElse(new ClienteLocalEntity());
                entity.setId(evento.getId());
                entity.setClienteId(evento.getClienteId());
                entity.setNombre(evento.getNombre());
                entity.setEstado(evento.getEstado());
                clienteLocalJpaRepository.save(entity);
            }
            case "ELIMINADO" -> clienteLocalJpaRepository.findById(evento.getId())
                    .ifPresent(e -> {
                        e.setEstado(false);
                        clienteLocalJpaRepository.save(e);
                    });
        }
    }
}
