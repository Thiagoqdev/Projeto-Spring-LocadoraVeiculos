package com.locadoraveiculo.locadoraveiculosapp.model;


import jakarta.persistence.*;

@Entity
public class Usuario {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long usuario_id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;

    private TipoUsuario tipoUsuario;


    private enum TipoUsuario {
        ClIENTE,
        ADMINISTRADOR
    }
}
