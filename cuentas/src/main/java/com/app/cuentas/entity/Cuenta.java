package com.app.cuentas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CUENTAS")
@SequenceGenerator(name="CTA_SEQUENCE_GENERATOR", sequenceName="CTA_SEQ", initialValue=1, allocationSize=1)
public class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "CTA_SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "CTA_ID")
    private Integer ctaId;
    @Column(nullable = false)
    private Integer ctaCliId;
    @Column(nullable = false)
    private String ctaNumCuenta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ctaTipoCuenta", referencedColumnName = "TCTA_ID")
    private TipoCuenta ctaTipoCuenta;
    @Column(nullable = false, length = 20)
    private BigDecimal ctaSaldoTotal;
    @Column(nullable = false, length = 1)
    private String ctaEstado;
    @Column(nullable = false)
    private Date ctaFeCrea;
    private Date ctaFeActualiza;

//    @OneToMany(mappedBy = "movCtaId", fetch = FetchType.LAZY)
//    private List<Movimiento> movimientos;

    public Cuenta(String numCuenta){
        this.ctaNumCuenta = numCuenta;
    }
}
