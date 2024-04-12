package com.app.cuentas.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CuentaReq {
    @NotEmpty(message = "Número de cuenta es requerido")
    private String numeroCuenta;

    @NotEmpty(message = "Tipo de cuenta (Ahorros, Corriente) es requerido")
    private String tipoCuenta;

    @NotNull(message = "Saldo inicial es requerido")
    private BigDecimal saldoInicial;

    @NotEmpty(message = "Identificación del cliente es requerido")
    private String identificacion;
}
