package com.app.clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="CLIENTES")
@SequenceGenerator(name="CLI_SEQUENCE_GENERATOR", sequenceName="CLI_SEQ", initialValue=1, allocationSize=1)
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "CLI_SEQUENCE_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "CLI_ID")
    private Integer cliId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cliPerId", referencedColumnName = "PER_ID")
    private Persona persona;

    @Column(nullable = false, length = 200)
    private String cliContra;

    @Column(nullable = false, length = 1)
    private String cliEstado;

    @Column(nullable = false)
    private Date cliFeCrea;
    private Date cliFeActualiza;
}
