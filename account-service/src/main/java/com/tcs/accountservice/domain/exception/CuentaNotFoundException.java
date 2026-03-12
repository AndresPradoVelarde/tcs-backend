package com.tcs.accountservice.domain.exception;

public class CuentaNotFoundException extends RuntimeException {

    public CuentaNotFoundException(Long id) {
        super("Cuenta no encontrada con id: " + id);
    }
}
