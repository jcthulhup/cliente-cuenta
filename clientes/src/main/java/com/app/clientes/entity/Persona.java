package com.app.clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="DEVSU_PERSONAS")
@SequenceGenerator(name="PER_SEQUENCE_GENERATOR", sequenceName="PER_SEQ", initialValue=1, allocationSize=1)
public class Persona {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "PER_SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "PER_ID")
    private Integer perId;
    @Column(nullable = false, length = 50)
    private String perNombre;
    @Column(nullable = false, length = 1)
    private String perGenero;
    @Column(nullable = false, length = 3)
    private Integer perEdad;
    @Column(nullable = false, length = 15)
    private String perIdentificacion;
    @Column(nullable = false, length = 50)
    private String perDireccion;
    @Column(nullable = false, length = 20)
    private String perTelefono;
    @Column(nullable = false)
    private Date perFeCrea;
    private Date perFeActualiza;

    public Persona(String identificacion){
        this.perIdentificacion = identificacion;
    }
}
