package com.app.cuentas.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class CuentaDto {
    private String fecha;
    private String cliente;
    private String numCuenta;
    private String tipoCuenta;
    private boolean estado;
    private BigDecimal saldoInicioMovimiento;
    private BigDecimal movimiento;
    private BigDecimal saldoFinMovimiento;
    private BigDecimal saldoDisponibleCuenta;
}
