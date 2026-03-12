package com.tcs.clientservice.infrastructure.adapter.in.rest.handler;

import com.tcs.clientservice.domain.exception.ClienteAlreadyExistsException;
import com.tcs.clientservice.domain.exception.ClienteNotFoundException;
import com.tcs.clientservice.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleClienteAlreadyExists(ClienteAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleClienteNotFound(ClienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {
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
