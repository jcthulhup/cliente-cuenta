package com.app.cuentas.controller;

import com.app.cuentas.dto.CuentaDto;
import com.app.cuentas.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("")
    public List<CuentaDto> reporteMovimientos(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern="dd/MM/yyyy") Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern="dd/MM/yyyy") Date fechaFin,
            @RequestParam("identificacion") String identificacion) {
        log.info("reporteMovimientos -> movimientos");
        return reporteService.obtenerReporteMovimientos(fechaInicio, fechaFin, identificacion);
    }
}
