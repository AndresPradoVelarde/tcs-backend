package com.tcs.accountservice.domain.port.out;

import com.tcs.accountservice.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoRepositoryPort {

    Movimiento save(Movimiento movimiento);

    Optional<Movimiento> findById(Long id);

    List<Movimiento> findAll();

    void deleteById(Long id);

    List<Movimiento> findByCuentaId(Long cuentaId);

    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
