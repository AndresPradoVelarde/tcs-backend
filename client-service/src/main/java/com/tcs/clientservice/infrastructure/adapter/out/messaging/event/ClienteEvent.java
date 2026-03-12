package com.tcs.clientservice.infrastructure.adapter.out.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEvent {

    private Long id;
    private String clienteId;
    private String nombre;
    private Boolean estado;
    private String tipoEvento; // CREADO, ACTUALIZADO, ELIMINADO
}
