package com.tcs.accountservice.infrastructure.adapter.out.persistence;

import com.tcs.accountservice.domain.port.out.ClienteLocalRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClienteLocalRepositoryAdapter implements ClienteLocalRepositoryPort {

    private final ClienteLocalJpaRepository clienteLocalJpaRepository;

    @Override
    public boolean existsActiveById(Long id) {
        return clienteLocalJpaRepository.findById(id)
                .map(c -> c.getEstado())
                .orElse(false);
    }
}
