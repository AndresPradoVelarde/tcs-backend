package com.tcs.accountservice.infrastructure.adapter.out.persistence;

import com.tcs.accountservice.domain.model.Cuenta;
import com.tcs.accountservice.domain.port.out.CuentaRepositoryPort;
import com.tcs.accountservice.infrastructure.adapter.out.persistence.mapper.CuentaPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CuentaRepositoryAdapter implements CuentaRepositoryPort {

    private final CuentaJpaRepository cuentaJpaRepository;
    private final CuentaPersistenceMapper mapper;

    @Override
    public Cuenta save(Cuenta cuenta) {
        return mapper.toDomain(cuentaJpaRepository.save(mapper.toEntity(cuenta)));
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return cuentaJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cuenta> findByEstadoTrue() {
        return cuentaJpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
        return cuentaJpaRepository.findByNumeroCuenta(numeroCuenta).map(mapper::toDomain);
    }

    @Override
    public List<Cuenta> findByClienteId(Long clienteId) {
        return cuentaJpaRepository.findByClienteId(clienteId).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
