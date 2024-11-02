package com.locadoraveiculo.locadoraveiculosapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
