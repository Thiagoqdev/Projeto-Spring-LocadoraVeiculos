package com.locadoraveiculo.locadoraveiculosapp.model;

import jakarta.persistence.*;



@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String placa;
    private String modelo;
    private String marca;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;


    public enum TipoVeiculo {
        MOTO,
        CARRO,
        CAMINHAO
    }


}
