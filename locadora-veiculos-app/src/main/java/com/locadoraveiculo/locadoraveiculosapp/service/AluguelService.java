package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AluguelService {

    private AluguelRepository aluguelRepository;

    @Autowired
    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }


}
