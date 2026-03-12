package com.tcs.accountservice.domain.port.out;

import com.tcs.accountservice.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {

    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> findById(Long id);

    List<Cuenta> findByEstadoTrue();

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    List<Cuenta> findByClienteId(Long clienteId);
}
