package com.tcs.accountservice.infrastructure.adapter.in.rest;

import com.tcs.accountservice.domain.port.in.CuentaServicePort;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.CuentaRequest;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.CuentaResponse;
import com.tcs.accountservice.infrastructure.adapter.in.rest.mapper.CuentaRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaServicePort cuentaServicePort;
    private final CuentaRestMapper mapper;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CuentaRequest request) {
        var cuenta = cuentaServicePort.crear(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Cuenta creada exitosamente", mapper.toResponse(cuenta)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        var cuenta = cuentaServicePort.obtenerPorId(id);
        return ResponseEntity.ok(ApiResponse.ok(mapper.toResponse(cuenta)));
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        var cuentas = cuentaServicePort.obtenerTodos().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(cuentas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody CuentaRequest request) {
        var cuenta = cuentaServicePort.actualizar(id, mapper.toDomain(request));
        return ResponseEntity.ok(ApiResponse.ok(mapper.toResponse(cuenta)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        cuentaServicePort.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
