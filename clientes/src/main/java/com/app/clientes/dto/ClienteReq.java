package com.app.clientes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteReq extends PersonaReq {
    private Integer id;

    @NotEmpty(message = "Contraseña es requerida")
    private String clave;

}