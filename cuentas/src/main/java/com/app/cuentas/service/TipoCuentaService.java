package com.app.cuentas.service;

import com.app.cuentas.entity.Cuenta;
import com.app.cuentas.entity.TipoCuenta;

public interface TipoCuentaService {
    TipoCuenta getByNombre(String nombre);
}
