package com.app.clientes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto {
    private Integer cliId;
    private String perIdentificacion;
    private String nombre;
}

