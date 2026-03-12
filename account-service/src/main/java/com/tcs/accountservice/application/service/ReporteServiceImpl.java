package com.tcs.accountservice.application.service;

import com.tcs.accountservice.domain.model.Cuenta;
import com.tcs.accountservice.domain.model.Movimiento;
import com.tcs.accountservice.domain.port.in.ReporteServicePort;
import com.tcs.accountservice.domain.port.out.CuentaRepositoryPort;
import com.tcs.accountservice.domain.port.out.MovimientoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteServicePort {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final MovimientoRepositoryPort movimientoRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public Map<Cuenta, List<Movimiento>> generarReporte(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Cuenta> cuentas = cuentaRepositoryPort.findByClienteId(clienteId);

        Map<Cuenta, List<Movimiento>> reporte = new LinkedHashMap<>();
        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepositoryPort
                    .findByCuentaIdAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
            reporte.put(cuenta, movimientos);
        }

        return reporte;
    }
}
