package com.app.cuentas.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Currency;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class MovimientoReq {
    @NotEmpty(message = "Número de cuenta es requerido")
    private String numeroCuenta;

    @NotEmpty(message = "Tipo de cuenta (Ahorros, Corriente) es requerido")
    private String tipoCuenta;

    @NotNull(message = "Monto de movimiento es requerido")
    @Digits(integer=18, fraction=2)
    private BigDecimal monto;
}
