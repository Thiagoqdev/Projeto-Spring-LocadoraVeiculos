package com.locadoraveiculo.locadoraveiculosapp.model;


import jakarta.persistence.*;

@Entity
@Table(name = "alugueis")
public class Aluguel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private int codVeiculo;
    private int valorCobrado;

    @Enumerated(EnumType.STRING)
    private tipoPagamento tipoPagamento;

    public enum tipoPagamento{
        AVISTA,
        CREDITO,
        DEBITO
    }

}
