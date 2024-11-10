package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.service.AgenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencias")
@Tag(name = "Agências", description = "API para gerenciamento de agências")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    @Operation(summary = "Criar agência", description = "Cria uma nova agência")
    public ResponseEntity<Agencia> criarAgencia(@RequestBody Agencia agencia) {
        Agencia novaAgencia = agenciaService.criarAgencia(agencia);
        return new ResponseEntity<>(novaAgencia, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar agências", description = "Retorna uma lista de todas as agências")
    public ResponseEntity<Page<Agencia>> listarAgencias(
        @RequestParam(defaultValue = "0") int pagina, 
        @RequestParam(defaultValue = "10") int tamanho
    ) {
        Page<Agencia> agencias = agenciaService.listarAgencias(pagina, tamanho);
        return ResponseEntity.ok(agencias);
    }

    @GetMapping("/{numeroAgencia}")
    @Operation(summary = "Buscar agência por ID", description = "Retorna uma agência específica pelo seu número")
    public ResponseEntity<Agencia> buscarAgenciaPorId(
        @Parameter(description = "Número da agência") 
        @PathVariable Long numeroAgencia
    ) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{numeroAgencia}")
    @Operation(summary = "Atualizar agência", description = "Atualiza os dados de uma agência existente")
    public ResponseEntity<Agencia> atualizarAgencia(
        @Parameter(description = "Número da agência") @PathVariable Long numeroAgencia, 
        @RequestBody Agencia agencia
    ) {
        Agencia agenciaAtualizada = agenciaService.atualizarAgencia(numeroAgencia, agencia);
        return ResponseEntity.ok(agenciaAtualizada);
    }

    @DeleteMapping("/{numeroAgencia}")
    @Operation(summary = "Deletar agência", description = "Remove uma agência do sistema")
    public ResponseEntity<Void> deletarAgencia(
        @Parameter(description = "Número da agência") @PathVariable Long numeroAgencia
    ) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia)
                .map(agenciaExistente -> {
                    agenciaService.removerAgencia(numeroAgencia);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}