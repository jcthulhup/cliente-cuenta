package com.app.cuentas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TIPO_CUENTAS")
public class TipoCuenta {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "TCTA_ID")
    private Integer tctaId;
    @Column(nullable = false)
    private String tctaNombre;
}
