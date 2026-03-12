package com.tcs.clientservice.domain.exception;

public class ClienteAlreadyExistsException extends RuntimeException {

    public ClienteAlreadyExistsException(String campo, String valor) {
        super("Ya existe un cliente con " + campo + ": " + valor);
    }
}
