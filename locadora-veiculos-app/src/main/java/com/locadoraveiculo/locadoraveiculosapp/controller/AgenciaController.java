package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.service.AgenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/agencias")
@Tag(name = "Agências", description = "API para gerenciamento de agências")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    @Operation(summary = "Criar agência", description = "Cria uma nova agência")
    public Agencia criarAgencia(@RequestBody Agencia agencia) {
        return agenciaService.criarAgencia(agencia);
    }

    @GetMapping
    @Operation(summary = "Listar agências", description = "Retorna uma lista de todas as agências")
    public Page<Agencia> listarAgencias(@RequestParam int pagina, @RequestParam int tamanho) {
        return agenciaService.listarAgencias(pagina, tamanho);
    }

    @GetMapping("/{numeroAgencia}")
    @Operation(summary = "Buscar agência por ID", description = "Retorna uma agência específica pelo seu número")
    public Agencia buscarAgenciaPorId(@Parameter(description = "Número da agência") @PathVariable Long numeroAgencia) {
        return agenciaService.buscarAgenciaPorCodigo(numeroAgencia);
    }


    @PutMapping("/{numeroAgencia}")
    @Operation(summary = "Atualizar agência", description = "Atualiza os dados de uma agência existente")
    public ResponseEntity<Agencia> atualizarAgencia(@Parameter(description = "Número da agência") @PathVariable Long numeroAgencia, @RequestBody Agencia agencia) {
        Agencia agenciaAtualizado = agenciaService.atualizarAgencia(numeroAgencia, agencia);
        return ResponseEntity.ok().body(agenciaAtualizado);
    }

    @DeleteMapping("/{numeroAgencia}")
    @Operation(summary = "Deletar agência", description = "Remove uma agência do sistema")
    public void DeletarAgencia(@Parameter(description = "Número da agência") @PathVariable Long numeroAgencia) {
        agenciaService.removerAgencia(numeroAgencia);
    }
}