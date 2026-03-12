package com.tcs.clientservice.infrastructure.adapter.out.persistence;

import com.tcs.clientservice.domain.model.Cliente;
import com.tcs.clientservice.domain.port.out.ClienteRepositoryPort;
import com.tcs.clientservice.infrastructure.adapter.out.persistence.mapper.ClientePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;
    private final ClientePersistenceMapper mapper;

    @Override
    public Cliente save(Cliente cliente) {
        return mapper.toDomain(clienteJpaRepository.save(mapper.toEntity(cliente)));
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cliente> findByEstadoTrue() {
        return clienteJpaRepository.findByEstadoTrue().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Cliente> findByClienteId(String clienteId) {
        return clienteJpaRepository.findByClienteId(clienteId).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> findByIdentificacion(String identificacion) {
        return clienteJpaRepository.findByIdentificacion(identificacion).map(mapper::toDomain);
    }
}
