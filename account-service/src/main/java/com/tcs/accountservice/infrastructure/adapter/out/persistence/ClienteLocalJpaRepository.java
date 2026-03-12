package com.tcs.accountservice.infrastructure.adapter.out.persistence;

import com.tcs.accountservice.infrastructure.adapter.out.persistence.entity.ClienteLocalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteLocalJpaRepository extends JpaRepository<ClienteLocalEntity, Long> {

    Optional<ClienteLocalEntity> findByClienteId(String clienteId);
}
