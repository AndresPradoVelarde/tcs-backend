package com.tcs.clientservice.application.service;

import com.tcs.clientservice.domain.exception.ClienteAlreadyExistsException;
import com.tcs.clientservice.domain.exception.ClienteNotFoundException;
import com.tcs.clientservice.domain.model.Cliente;
import com.tcs.clientservice.domain.port.in.ClienteServicePort;
import com.tcs.clientservice.domain.port.out.ClienteRepositoryPort;
import com.tcs.clientservice.infrastructure.adapter.out.messaging.ClienteEventPublisher;
import com.tcs.clientservice.infrastructure.adapter.out.messaging.event.ClienteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepositoryPort;

    private final ClienteEventPublisher clienteEventPublisher;

    @Override
    @Transactional
    public Cliente crear(Cliente cliente) {
        clienteRepositoryPort.findByIdentificacion(cliente.getIdentificacion())
                .ifPresent(c ->
                { throw new ClienteAlreadyExistsException("identificacion", cliente.getIdentificacion()); });
        clienteRepositoryPort.findByClienteId(cliente.getClienteId())
                .ifPresent(c ->
                { throw new ClienteAlreadyExistsException("clienteId", cliente.getClienteId()); });
        Cliente guardado = clienteRepositoryPort.save(cliente);
        clienteEventPublisher.publicar(new ClienteEvent(
                guardado.getId(),
                guardado.getClienteId(),
                guardado.getNombre(),
                guardado.getEstado(),
                "CREADO"));
        return guardado;
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente obtenerPorId(Long id) {
        Cliente cliente = clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
        if (!cliente.getEstado()) {
            throw new ClienteNotFoundException(id);
        }
        return cliente;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodos() {
        return clienteRepositoryPort.findByEstadoTrue();
    }

    @Override
    @Transactional
    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente existente = clienteRepositoryPort.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));

        existente.setNombre(cliente.getNombre());
        existente.setGenero(cliente.getGenero());
        existente.setEdad(cliente.getEdad());
        existente.setIdentificacion(cliente.getIdentificacion());
        existente.setDireccion(cliente.getDireccion());
        existente.setTelefono(cliente.getTelefono());
        existente.setContrasena(cliente.getContrasena());
        existente.setEstado(cliente.getEstado());

        Cliente actualizado = clienteRepositoryPort.save(existente);
        clienteEventPublisher.publicar(new ClienteEvent(
                actualizado.getId(),
                actualizado.getClienteId(),
                actualizado.getNombre(),
                actualizado.getEstado(),
                "ACTUALIZADO"));
        return actualizado;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cliente existente = clienteRepositoryPort.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
        existente.setEstado(false);
        clienteRepositoryPort.save(existente);
        clienteEventPublisher.publicar(new ClienteEvent(
                existente.getId(),
                existente.getClienteId(),
                existente.getNombre(),
                false,
                "ELIMINADO"));
    }
}
