package com.app.cuentas.service;

import com.app.cuentas.dto.CuentaDto;

import java.util.Date;
import java.util.List;

public interface ReporteService {
    List<CuentaDto> obtenerReporteMovimientos(Date i, Date f, String identificacion);
}
