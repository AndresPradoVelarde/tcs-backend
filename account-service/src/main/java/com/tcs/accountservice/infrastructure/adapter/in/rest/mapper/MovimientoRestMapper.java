package com.tcs.accountservice.infrastructure.adapter.in.rest.mapper;

import com.tcs.accountservice.domain.model.Movimiento;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.MovimientoRequest;
import com.tcs.accountservice.infrastructure.adapter.in.rest.dto.MovimientoResponse;
import org.springframework.stereotype.Component;

@Component
public class MovimientoRestMapper {

    public Movimiento toDomain(MovimientoRequest request) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(request.getTipoMovimiento());
        movimiento.setValor(request.getValor());
        movimiento.setCuentaId(request.getCuentaId());
        return movimiento;
    }

    public MovimientoResponse toResponse(Movimiento movimiento) {
        MovimientoResponse response = new MovimientoResponse();
        response.setId(movimiento.getId());
        response.setFecha(movimiento.getFecha());
        response.setTipoMovimiento(movimiento.getTipoMovimiento());
        response.setValor(movimiento.getValor());
        response.setSaldo(movimiento.getSaldo());
        response.setCuentaId(movimiento.getCuentaId());
        return response;
    }
}
