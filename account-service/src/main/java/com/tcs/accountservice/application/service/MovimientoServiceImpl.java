package com.tcs.accountservice.application.service;

import com.tcs.accountservice.domain.exception.CuentaNotFoundException;
import com.tcs.accountservice.domain.exception.MovimientoNotFoundException;
import com.tcs.accountservice.domain.exception.SaldoNoDisponibleException;
import com.tcs.accountservice.domain.model.Cuenta;
import com.tcs.accountservice.domain.model.Movimiento;
import com.tcs.accountservice.domain.port.in.MovimientoServicePort;
import com.tcs.accountservice.domain.port.out.CuentaRepositoryPort;
import com.tcs.accountservice.domain.port.out.MovimientoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoServicePort {

    private final MovimientoRepositoryPort movimientoRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;

    @Override
    @Transactional
    public Movimiento registrar(Movimiento movimiento) {
        Cuenta cuenta = cuentaRepositoryPort.findById(movimiento.getCuentaId())
                .orElseThrow(() -> new CuentaNotFoundException(movimiento.getCuentaId()));

        BigDecimal saldoActual = cuenta.getSaldoInicial();
        BigDecimal nuevoSaldo = saldoActual.add(movimiento.getValor());

        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoNoDisponibleException();
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepositoryPort.save(cuenta);

        movimiento.setFecha(LocalDateTime.now());
        movimiento.setSaldo(nuevoSaldo);

        return movimientoRepositoryPort.save(movimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public Movimiento obtenerPorId(Long id) {
        return movimientoRepositoryPort.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> obtenerTodos() {
        return movimientoRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> obtenerPorCuentaId(Long cuentaId) {
        return movimientoRepositoryPort.findByCuentaId(cuentaId);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        movimientoRepositoryPort.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException(id));
        movimientoRepositoryPort.deleteById(id);
    }
}
