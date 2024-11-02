package com.locadoraveiculo.locadoraveiculosapp.model;

import java.util.Optional;

public class Veiculo {

    private int id;
    private String placa;
    private String modelo;
    private String marca;
    private TipoVeiculo tipoVeiculo;

    public enum TipoVeiculo {
        MOTO,
        CARRO,
        CAMINHAO
    }


}
