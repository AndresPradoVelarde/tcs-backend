package com.tcs.clientservice.infrastructure.adapter.in.rest;

import com.tcs.clientservice.domain.port.in.ClienteServicePort;
import com.tcs.clientservice.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.tcs.clientservice.infrastructure.adapter.in.rest.dto.ClienteRequest;
import com.tcs.clientservice.infrastructure.adapter.in.rest.dto.ClienteResponse;
import com.tcs.clientservice.infrastructure.adapter.in.rest.mapper.ClienteRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServicePort clienteServicePort;
    private final ClienteRestMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponse>> crear(@Valid @RequestBody ClienteRequest request) {
        var cliente = clienteServicePort.crear(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Cliente creado exitosamente", mapper.toResponse(cliente)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> obtenerPorId(@PathVariable Long id) {
        var cliente = clienteServicePort.obtenerPorId(id);
        return ResponseEntity.ok(ApiResponse.ok(mapper.toResponse(cliente)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> obtenerTodos() {
        var clientes = clienteServicePort.obtenerTodos().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> actualizar(@PathVariable Long id,
                                                                   @Valid @RequestBody ClienteRequest request) {
        var cliente = clienteServicePort.actualizar(id, mapper.toDomain(request));
        return ResponseEntity.ok(ApiResponse.ok(mapper.toResponse(cliente)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        clienteServicePort.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
