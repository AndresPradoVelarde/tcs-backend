package com.tcs.accountservice.application.service;

import com.tcs.accountservice.domain.exception.ClienteNoEncontradoException;
import com.tcs.accountservice.domain.exception.CuentaAlreadyExistsException;
import com.tcs.accountservice.domain.exception.CuentaNotFoundException;
import com.tcs.accountservice.domain.model.Cuenta;
import com.tcs.accountservice.domain.port.in.CuentaServicePort;
import com.tcs.accountservice.domain.port.out.ClienteLocalRepositoryPort;
import com.tcs.accountservice.domain.port.out.CuentaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaServicePort {

    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteLocalRepositoryPort clienteLocalRepositoryPort;

    @Override
    @Transactional
    public Cuenta crear(Cuenta cuenta) {
        if (!clienteLocalRepositoryPort.existsActiveById(cuenta.getClienteId())) {
            throw new ClienteNoEncontradoException(cuenta.getClienteId());
        }
        cuentaRepositoryPort.findByNumeroCuenta(cuenta.getNumeroCuenta())
                .ifPresent(c ->
                { throw new CuentaAlreadyExistsException(cuenta.getNumeroCuenta()); });
        return cuentaRepositoryPort.save(cuenta);
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta obtenerPorId(Long id) {
        Cuenta cuenta = cuentaRepositoryPort.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException(id));
        if (!cuenta.getEstado()) {
            throw new CuentaNotFoundException(id);
        }
        return cuenta;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> obtenerTodos() {
        return cuentaRepositoryPort.findByEstadoTrue();
    }

    @Override
    @Transactional
    public Cuenta actualizar(Long id, Cuenta cuenta) {
        Cuenta existente = cuentaRepositoryPort.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException(id));

        existente.setNumeroCuenta(cuenta.getNumeroCuenta());
        existente.setTipoCuenta(cuenta.getTipoCuenta());
        existente.setSaldoInicial(cuenta.getSaldoInicial());
        existente.setEstado(cuenta.getEstado());

        return cuentaRepositoryPort.save(existente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cuenta existente = cuentaRepositoryPort.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException(id));
        existente.setEstado(false);
        cuentaRepositoryPort.save(existente);
    }
}
