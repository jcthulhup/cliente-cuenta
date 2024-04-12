package com.app.cuentas.service;

import com.app.cuentas.dto.CuentaReq;
import com.app.cuentas.entity.Cuenta;

import java.util.List;

public interface CuentaService {

    Cuenta create(CuentaReq req);

    List<Cuenta> getAll();

    Cuenta getById(Integer cliId);

    List<Cuenta> getByIden(String iden);

    Cuenta update(Integer id, CuentaReq req);

    void delete(Integer id);

}
