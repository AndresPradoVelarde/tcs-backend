package com.tcs.clientservice.infrastructure.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.clientservice.domain.exception.ClienteNotFoundException;
import com.tcs.clientservice.domain.model.Cliente;
import com.tcs.clientservice.domain.port.in.ClienteServicePort;
import com.tcs.clientservice.infrastructure.adapter.in.rest.dto.ClienteRequest;
import com.tcs.clientservice.infrastructure.adapter.in.rest.handler.GlobalExceptionHandler;
import com.tcs.clientservice.infrastructure.adapter.in.rest.mapper.ClienteRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ClienteServicePort clienteServicePort;

    @Mock
    private ClienteRestMapper mapper;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(clienteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void postClientes_debeRetornar201_cuandoRequestEsValido() throws Exception {
        ClienteRequest request = new ClienteRequest(
                "Jose Lema",
                "Masculino",
                35,
                "1234567890",
                "Otavalo sn y principal",
                "098254785",
                "Jose",
                "1234",
                true);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Jose Lema");
        cliente.setEstado(true);

        when(mapper.toDomain(any())).thenReturn(cliente);
        when(clienteServicePort.crear(any())).thenReturn(cliente);
        when(mapper.toResponse(any())).thenReturn(null);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Cliente creado exitosamente"));
    }

    @Test
    void getClientes_debeRetornar404_cuandoClienteNoExiste() throws Exception {
        when(clienteServicePort.obtenerPorId(99L)).thenThrow(new ClienteNotFoundException(99L));

        mockMvc.perform(get("/clientes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}
