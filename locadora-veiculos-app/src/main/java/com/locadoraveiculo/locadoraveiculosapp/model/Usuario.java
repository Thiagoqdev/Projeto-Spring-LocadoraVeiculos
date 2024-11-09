package com.locadoraveiculo.locadoraveiculosapp.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nome;
    private String email;
    private String cpf;
}
