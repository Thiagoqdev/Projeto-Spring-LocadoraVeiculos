package com.locadoraveiculo.locadoraveiculosapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String placa;

    private String modelo;
    private String marca;
    private String ano;

    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluguel> alugueis = new ArrayList<>();

    public enum TipoVeiculo {
        MOTO,
        CARRO,
        CAMINHAO
    }


}
