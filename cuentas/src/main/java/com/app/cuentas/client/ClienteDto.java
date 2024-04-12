package com.app.cuentas.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDto {
    private Integer cliId;
    private String perIdentificacion;
    private String nombre;
}
