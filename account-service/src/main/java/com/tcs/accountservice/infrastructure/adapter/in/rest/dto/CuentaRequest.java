package com.tcs.accountservice.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaRequest {

    @NotBlank
    private String numeroCuenta;

    @NotBlank
    private String tipoCuenta;

    @NotNull
    private BigDecimal saldoInicial;

    @NotNull
    private Boolean estado;

    @NotNull
    private Long clienteId;
}
