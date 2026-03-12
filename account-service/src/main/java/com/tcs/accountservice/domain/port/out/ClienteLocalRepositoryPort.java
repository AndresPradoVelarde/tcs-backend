package com.tcs.accountservice.domain.port.out;

public interface ClienteLocalRepositoryPort {

    boolean existsActiveById(Long id);
}
