package com.tcs.accountservice.infrastructure.adapter.out.persistence;

import com.tcs.accountservice.domain.model.Movimiento;
import com.tcs.accountservice.domain.port.out.MovimientoRepositoryPort;
import com.tcs.accountservice.infrastructure.adapter.out.persistence.mapper.MovimientoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final MovimientoJpaRepository movimientoJpaRepository;
    private final MovimientoPersistenceMapper mapper;

    @Override
    public Movimiento save(Movimiento movimiento) {
        return mapper.toDomain(movimientoJpaRepository.save(mapper.toEntity(movimiento)));
    }

    @Override
    public Optional<Movimiento> findById(Long id) {
        return movimientoJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Movimiento> findAll() {
        return movimientoJpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        movimientoJpaRepository.deleteById(id);
    }

    @Override
    public List<Movimiento> findByCuentaId(Long cuentaId) {
        return movimientoJpaRepository.findByCuentaId(cuentaId).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return movimientoJpaRepository.findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
