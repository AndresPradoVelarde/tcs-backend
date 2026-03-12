package com.tcs.clientservice.domain.port.in;

import com.tcs.clientservice.domain.model.Cliente;

import java.util.List;

public interface ClienteServicePort {

    Cliente crear(Cliente cliente);

    Cliente obtenerPorId(Long id);

    List<Cliente> obtenerTodos();

    Cliente actualizar(Long id, Cliente cliente);

    void eliminar(Long id);
}
