package com.app.cuentas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="DEVSU_MOVIMIENTOS")
@SequenceGenerator(name="MOV_SEQUENCE_GENERATOR", sequenceName="MOV_SEQ", initialValue=1, allocationSize=1)
public class Movimiento {
    @Id
    @GeneratedValue(generator = "MOV_SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "MOV_ID")
    private Integer movId;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date movFecha;
    @Column(nullable = false)
    private BigDecimal movValor;
    @Column(nullable = false)
    private BigDecimal movSaldoIni;
    @Column(nullable = false)
    private BigDecimal movSaldoFin;
    @Column(nullable = false, length = 1)
    private String movEstado;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movCtaId", referencedColumnName = "CTA_ID")
    private Cuenta movCtaId;
}
