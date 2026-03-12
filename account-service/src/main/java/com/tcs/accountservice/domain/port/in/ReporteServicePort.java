package com.tcs.accountservice.domain.port.in;

import com.tcs.accountservice.domain.model.Cuenta;
import com.tcs.accountservice.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReporteServicePort {

    Map<Cuenta, List<Movimiento>> generarReporte(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
