package com.tcs.accountservice.infrastructure.adapter.out.persistence;

import com.tcs.accountservice.infrastructure.adapter.out.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoJpaRepository extends JpaRepository<MovimientoEntity, Long> {

    List<MovimientoEntity> findByCuentaId(Long cuentaId);

    List<MovimientoEntity> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
