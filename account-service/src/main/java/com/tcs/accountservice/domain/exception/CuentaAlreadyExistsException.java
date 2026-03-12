package com.tcs.accountservice.domain.exception;

public class CuentaAlreadyExistsException extends RuntimeException {

    public CuentaAlreadyExistsException(String numeroCuenta) {
        super("Ya existe una cuenta con número: " + numeroCuenta);
    }
}
