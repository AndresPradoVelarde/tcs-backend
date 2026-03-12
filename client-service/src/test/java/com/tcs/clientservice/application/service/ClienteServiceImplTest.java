package com.tcs.clientservice.application.service;

import com.tcs.clientservice.domain.model.Cliente;
import com.tcs.clientservice.domain.port.out.ClienteRepositoryPort;
import com.tcs.clientservice.infrastructure.adapter.out.messaging.ClienteEventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    @Mock
    private ClienteEventPublisher clienteEventPublisher;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void debeCrearCliente_exitosamente() {
        Cliente nuevo = new Cliente();
        nuevo.setNombre("Jose Lema");
        nuevo.setGenero("Masculino");
        nuevo.setEdad(35);
        nuevo.setIdentificacion("1234567890");
        nuevo.setDireccion("Otavalo sn y principal");
        nuevo.setTelefono("098254785");
        nuevo.setClienteId("Jose");
        nuevo.setContrasena("1234");
        nuevo.setEstado(true);

        Cliente guardado = new Cliente();
        guardado.setId(1L);
        guardado.setNombre("Jose Lema");
        guardado.setClienteId("Jose");
        guardado.setEstado(true);

        when(clienteRepositoryPort.findByIdentificacion("1234567890")).thenReturn(Optional.empty());
        when(clienteRepositoryPort.findByClienteId("Jose")).thenReturn(Optional.empty());
        when(clienteRepositoryPort.save(nuevo)).thenReturn(guardado);
        doNothing().when(clienteEventPublisher).publicar(any());

        Cliente resultado = clienteService.crear(nuevo);

        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNombre()).isEqualTo("Jose Lema");
        verify(clienteRepositoryPort).save(nuevo);
        verify(clienteEventPublisher).publicar(any());
    }
}
