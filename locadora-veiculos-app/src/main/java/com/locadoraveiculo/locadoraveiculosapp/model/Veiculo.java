package com.locadoraveiculo.locadoraveiculosapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String placa;
    private String modelo;
    private String marca;
    private String ano;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;


    public enum TipoVeiculo {
        MOTO,
        CARRO,
        CAMINHAO
    }


}
