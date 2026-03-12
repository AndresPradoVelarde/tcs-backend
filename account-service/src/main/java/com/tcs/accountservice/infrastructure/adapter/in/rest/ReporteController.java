package com.tcs.accountservice.infrastructure.adapter.in.rest;

import com.tcs.accountservice.domain.model.Cuenta;
import com.tcs.accountservice.domain.model.Movimiento;
import com.tcs.accountservice.domain.port.in.ReporteServicePort;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.ReporteResponse;
import com.tcs.accountservice.infrastructure.adapter.out.persistence.ClienteLocalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteServicePort reporteServicePort;
    private final ClienteLocalJpaRepository clienteLocalJpaRepository;

    @GetMapping
    public ResponseEntity<?> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {

        String nombreCliente = clienteLocalJpaRepository.findById(clienteId)
                .map(c -> c.getNombre())
                .orElse("Desconocido");

        Map<Cuenta, List<Movimiento>> reporte = reporteServicePort.generarReporte(
                clienteId,
                fechaInicio.atStartOfDay(),
                fechaFin.atTime(23, 59, 59));

        List<ReporteResponse> respuesta = new ArrayList<>();
        for (Map.Entry<Cuenta, List<Movimiento>> entry : reporte.entrySet()) {
            Cuenta cuenta = entry.getKey();
            for (Movimiento mov : entry.getValue()) {
                ReporteResponse item = new ReporteResponse();
                item.setFecha(mov.getFecha());
                item.setCliente(nombreCliente);
                item.setNumeroCuenta(cuenta.getNumeroCuenta());
                item.setTipo(cuenta.getTipoCuenta());
                item.setSaldoInicial(cuenta.getSaldoInicial());
                item.setEstado(cuenta.getEstado());
                item.setMovimiento(mov.getValor());
                item.setSaldoDisponible(mov.getSaldo());
                respuesta.add(item);
            }
        }

        return ResponseEntity.ok(ApiResponse.ok(respuesta));
    }
}
