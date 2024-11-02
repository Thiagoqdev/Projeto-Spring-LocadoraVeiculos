package com.locadoraveiculo.locadoraveiculosapp.model;


import jakarta.persistence.*;


@Entity
@Table(name = "agencias")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroAgencia;
    private String nomeAgencia;
    private String enderecoAgencia;
}
