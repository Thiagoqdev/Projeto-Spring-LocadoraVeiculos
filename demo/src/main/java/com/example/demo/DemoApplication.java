package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class atv1PrSisII{

    public static void main(String[] args) {
        int limiteEstoque = 10000;
        Combustivel gasolina = new Combustivel ("Gasolina", limiteEstoque)
        int qtdAposReposicao = gasolina.reporEstoque(1000);
        System.out.println("qtdAposReposicao" + qtdAposReposicao);
        int qntrealmenteAbastecido1 = gassolina.abastecerVeiculo(51);
        System.out.println("qtd abastecida 1"   + qtdRealmenteAbastecido1);
        int qndRealmenteAbastecido2 = gasolina.abastecerVeiculo(10);
        System.out.println("qntrealmenteAbastecido2" + qntrealmenteAbastecido2);
        System.out.println("Estoque" + gasolina.mostrarEstoque());
    }
}



public class Combustivel{

    //Este conteudo foi perdido e precisa ser desenolvido novamente
}