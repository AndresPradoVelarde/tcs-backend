package com.tcs.accountservice.domain.port.in;

import com.tcs.accountservice.domain.model.Cuenta;

import java.util.List;

public interface CuentaServicePort {

    Cuenta crear(Cuenta cuenta);

    Cuenta obtenerPorId(Long id);

    List<Cuenta> obtenerTodos();

    Cuenta actualizar(Long id, Cuenta cuenta);

    void eliminar(Long id);
}
