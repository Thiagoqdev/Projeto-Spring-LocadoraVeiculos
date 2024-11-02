package com.locadoraveiculo.locadoraveiculosapp.model;

public class Aluguel {

    private int codigo;
    private int codVeiculo;
    private int valorCobrado;
    private tipoPagamento tipoPagamento;

    public enum tipoPagamento{
        AVISTA,
        CREDITO,
        DEBITO
    }

}
