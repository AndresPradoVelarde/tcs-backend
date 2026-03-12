package com.tcs.accountservice.infrastructure.adapter.out.persistence.mapper;

import com.tcs.accountservice.domain.model.Movimiento;
import com.tcs.accountservice.infrastructure.adapter.out.persistence.entity.MovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class MovimientoPersistenceMapper {

    public MovimientoEntity toEntity(Movimiento movimiento) {
        MovimientoEntity entity = new MovimientoEntity();
        entity.setId(movimiento.getId());
        entity.setFecha(movimiento.getFecha());
        entity.setTipoMovimiento(movimiento.getTipoMovimiento());
        entity.setValor(movimiento.getValor());
        entity.setSaldo(movimiento.getSaldo());
        entity.setCuentaId(movimiento.getCuentaId());
        return entity;
    }

    public Movimiento toDomain(MovimientoEntity entity) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(entity.getId());
        movimiento.setFecha(entity.getFecha());
        movimiento.setTipoMovimiento(entity.getTipoMovimiento());
        movimiento.setValor(entity.getValor());
        movimiento.setSaldo(entity.getSaldo());
        movimiento.setCuentaId(entity.getCuentaId());
        return movimiento;
    }
}
