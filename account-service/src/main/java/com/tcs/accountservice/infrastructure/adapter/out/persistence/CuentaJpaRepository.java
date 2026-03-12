package com.tcs.accountservice.infrastructure.adapter.out.persistence;

import com.tcs.accountservice.infrastructure.adapter.out.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {

    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);

    List<CuentaEntity> findByClienteId(Long clienteId);

    List<CuentaEntity> findByEstadoTrue();
}
