package com.app.cuentas.service;

import com.app.cuentas.dto.CuentaReq;
import com.app.cuentas.dto.MovimientoReq;
import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.Movimiento;

import java.util.List;

public interface MovimientoService {
    Movimiento create(MovimientoReq req);

    List<Movimiento> getAll();

    Movimiento getById(Integer movId);

    Movimiento update(Integer id, MovimientoReq req);

    void delete(Integer id);
}
