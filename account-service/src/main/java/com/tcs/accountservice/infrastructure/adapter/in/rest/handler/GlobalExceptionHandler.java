package com.tcs.accountservice.infrastructure.adapter.in.rest.handler;

import com.tcs.accountservice.domain.exception.ClienteNoEncontradoException;
import com.tcs.accountservice.domain.exception.CuentaAlreadyExistsException;
import com.tcs.accountservice.domain.exception.CuentaNotFoundException;
import com.tcs.accountservice.domain.exception.MovimientoNotFoundException;
import com.tcs.accountservice.domain.exception.SaldoNoDisponibleException;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ResponseEntity<?> handleClienteNoEncontrado(ClienteNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<?> handleCuentaNotFound(CuentaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(MovimientoNotFoundException.class)
    public ResponseEntity<?> handleMovimientoNotFound(MovimientoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(CuentaAlreadyExistsException.class)
    public ResponseEntity<?> handleCuentaAlreadyExists(CuentaAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(SaldoNoDisponibleException.class)
    public ResponseEntity<?> handleSaldoNoDisponible(SaldoNoDisponibleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(f -> fieldErrors.put(f.getField(), f.getDefaultMessage()));

        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Error de validación");
        response.setData(fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
