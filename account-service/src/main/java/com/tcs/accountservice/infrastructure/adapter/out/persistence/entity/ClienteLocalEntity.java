package com.tcs.accountservice.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes_local")
public class ClienteLocalEntity {

    @Id
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private String clienteId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;
}
