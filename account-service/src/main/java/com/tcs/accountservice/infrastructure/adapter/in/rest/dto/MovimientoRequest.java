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
public class MovimientoRequest {

    @NotBlank
    private String tipoMovimiento;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Long cuentaId;
}
