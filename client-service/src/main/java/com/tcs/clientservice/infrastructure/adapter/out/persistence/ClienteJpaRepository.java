package com.tcs.clientservice.infrastructure.adapter.out.persistence;

import com.tcs.clientservice.infrastructure.adapter.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByClienteId(String clienteId);

    Optional<ClienteEntity> findByIdentificacion(String identificacion);

    List<ClienteEntity> findByEstadoTrue();
}
