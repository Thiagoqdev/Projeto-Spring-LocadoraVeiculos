package com.locadoraveiculo.locadoraveiculosapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "alugueis")
public class Aluguel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aluguel_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numeroAgencia")
    private Agencia agencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    private BigDecimal valorCobrado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    public enum TipoPagamento{
        AVISTA,
        CREDITO,
        DEBITO
    }

}
