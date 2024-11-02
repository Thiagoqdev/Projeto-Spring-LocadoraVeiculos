package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencias")
public class AgenciaController {

    private final AgenciaService agenciaService;

    @Autowired
    public AgenciaController(AgenciaService agenciaService) {
        this.agenciaService = agenciaService;
    }

    @PostMapping
    public ResponseEntity<Agencia> criarAgencia(@RequestBody Agencia agencia) {
        Agencia novaAgencia = agenciaService.criarAgencia(agencia);
        return new ResponseEntity<>(novaAgencia, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Agencia>> listarAgencias() {
        List<Agencia> agencias = agenciaService.listarAgencias();
        return ResponseEntity.ok(agencias);
    }

    @GetMapping("/{numeroAgencia}")
    public ResponseEntity<Agencia> buscarAgenciaPorId(@PathVariable Long numeroAgencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{numeroAgencia}")
    public ResponseEntity<Agencia> atualizarAgencia(@PathVariable Long numeroAgencia, @RequestBody Agencia agencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(agenciaExistente -> {
                    agenciaExistente.setNomeAgencia(agencia.getNomeAgencia());
                    agenciaExistente.setEnderecoAgencia(agencia.getEnderecoAgencia());

                    Agencia agenciaAtualizada = agenciaService.atualizarAgencia(numeroAgencia, agenciaExistente);
                    return ResponseEntity.ok(agenciaAtualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{numeroAgencia}")
    public ResponseEntity<Void> DeletarAgencia(@PathVariable Long numeroAgencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(agencia -> {
                    agenciaService.removerAgencia(numeroAgencia);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
