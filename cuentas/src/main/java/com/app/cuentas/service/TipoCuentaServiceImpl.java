package com.app.cuentas.service;

import com.app.cuentas.entity.TipoCuenta;
import com.app.cuentas.repository.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoCuentaServiceImpl implements TipoCuentaService {

    @Autowired
    private TipoCuentaRepository tipoCuentaRepository;
    @Override
    public TipoCuenta getByNombre(String nombre) {
        return tipoCuentaRepository.findTCByNombre(nombre);
    }
}
