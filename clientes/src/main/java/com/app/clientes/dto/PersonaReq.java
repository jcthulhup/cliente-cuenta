package com.app.clientes.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonaReq {
    private Integer perId;

    @NotEmpty(message = "Nombre es requerido")
    private String nombres;

    @Size(max = 1, message = "Género debe ser M (masculino) o F (femenino)")
    @NotEmpty(message = "Género es requerido")
    private String genero;

    @NotNull(message = "Edad es requerido")
    private Integer edad;

    @NotEmpty(message = "Identificación es requerido")
    private String identificacion;

    @NotEmpty(message = "Dirección es requerida")
    private String direccion;

    @NotEmpty(message = "Teléfono es requerido")
    private String telefono;

}
