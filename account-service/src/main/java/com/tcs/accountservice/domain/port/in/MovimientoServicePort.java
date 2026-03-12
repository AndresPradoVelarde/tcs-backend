package com.tcs.accountservice.domain.port.in;

import com.tcs.accountservice.domain.model.Movimiento;

import java.util.List;

public interface MovimientoServicePort {

    Movimiento registrar(Movimiento movimiento);

    Movimiento obtenerPorId(Long id);

    List<Movimiento> obtenerTodos();

    List<Movimiento> obtenerPorCuentaId(Long cuentaId);

    void eliminar(Long id);
}
