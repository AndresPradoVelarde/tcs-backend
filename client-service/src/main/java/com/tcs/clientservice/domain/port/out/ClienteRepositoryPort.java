package com.tcs.clientservice.domain.port.out;

import com.tcs.clientservice.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    List<Cliente> findByEstadoTrue();

    Optional<Cliente> findByClienteId(String clienteId);

    Optional<Cliente> findByIdentificacion(String identificacion);
}
