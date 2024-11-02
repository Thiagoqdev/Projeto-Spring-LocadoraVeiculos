package com.locadoraveiculo.locadoraveiculosapp.controller;


import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.repository.VeiculoRepository;
import com.locadoraveiculo.locadoraveiculosapp.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{veiculo_id}")
    public ResponseEntity<Veiculo> buscarVeiculoPorId(@PathVariable Long veiculo_id) {
        return veiculoService.buscarVeiculoPorId(veiculo_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{veiculo_id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long veiculo_id, @RequestBody Veiculo veiculo) {
        return veiculoService.buscarVeiculoPorId(veiculo_id)
                .map(veiculoExistente -> {
                    Veiculo veiculoAtulizado = veiculoService.atualizarVeiculo(veiculo_id, veiculo);
                    return ResponseEntity.ok(veiculoAtulizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{veiculo_id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long veiculo_id) {
        return veiculoService.buscarVeiculoPorId(veiculo_id)
                .map(veiculo -> {
                    veiculoService.deletarVeiculo(veiculo_id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
