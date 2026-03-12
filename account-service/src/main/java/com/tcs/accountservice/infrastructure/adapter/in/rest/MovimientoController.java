package com.tcs.accountservice.infrastructure.adapter.in.rest;

import com.tcs.accountservice.domain.port.in.MovimientoServicePort;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.MovimientoRequest;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.MovimientoResponse;
import com.tcs.accountservice.infrastructure.adapter.in.rest.mapper.MovimientoRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoServicePort movimientoServicePort;
    private final MovimientoRestMapper mapper;

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody MovimientoRequest request) {
        var movimiento = movimientoServicePort.registrar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Movimiento registrado exitosamente", mapper.toResponse(movimiento)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        var movimiento = movimientoServicePort.obtenerPorId(id);
        return ResponseEntity.ok(ApiResponse.ok(mapper.toResponse(movimiento)));
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        var movimientos = movimientoServicePort.obtenerTodos().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.ok(movimientos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        movimientoServicePort.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
