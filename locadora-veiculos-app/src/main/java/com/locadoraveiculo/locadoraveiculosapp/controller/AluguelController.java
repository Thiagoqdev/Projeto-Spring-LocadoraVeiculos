package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alugueis")
@Tag(name = "Aluguéis", description = "API para gerenciamento de aluguéis de veículos")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping
    @Operation(summary = "Criar aluguel", description = "Cria um novo aluguel de veículo")
    public ResponseEntity<Aluguel> criarAluguel(@RequestBody Aluguel aluguel) {
        Aluguel novoAluguel = aluguelService.criarAluguel(aluguel);
        return new ResponseEntity<>(novoAluguel, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar aluguéis", description = "Retorna uma lista de todos os aluguéis")
    public ResponseEntity<Page<Aluguel>> listarAlugueis(
        @RequestParam(defaultValue = "0") int pagina, 
        @RequestParam(defaultValue = "10") int tamanho
    ) {
        Page<Aluguel> alugueis = aluguelService.listarAlugueis(pagina, tamanho);
        return ResponseEntity.ok(alugueis);
    }

    @GetMapping("/{aluguel_id}")
    @Operation(summary = "Buscar aluguel por ID", description = "Retorna um aluguel específico pelo seu ID")
    public ResponseEntity<Aluguel> buscarAluguelPorCodigo(
        @Parameter(description = "ID do aluguel") 
        @PathVariable Long aluguel_id
    ) {
        return aluguelService.buscarAluguelPorCodigo(aluguel_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{aluguel_id}")
    @Operation(summary = "Atualizar aluguel", description = "Atualiza os dados de um aluguel existente")
    public ResponseEntity<Aluguel> atualizarAluguel(
        @Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id, 
        @RequestBody Aluguel aluguel
    ) {
        try {
            Aluguel aluguelAtualizado = aluguelService.atualizarAluguel(aluguel_id, aluguel);
            return ResponseEntity.ok(aluguelAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{aluguel_id}")
    @Operation(summary = "Remover aluguel", description = "Remove um aluguel do sistema")
    public ResponseEntity<Void> removerAluguel(
        @Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id
    ) {
        try {
            aluguelService.deletarAluguel(aluguel_id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{aluguel_id}/finalizar")
    @Operation(summary = "Finalizar aluguel", description = "Finaliza um aluguel em andamento")
    public ResponseEntity<Aluguel> finalizarAluguel(
        @Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id
    ) {
        try {
            Aluguel aluguelfinalizado = aluguelService.finalizarAluguel(aluguel_id);
            return ResponseEntity.ok(aluguelfinalizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alugueis/ativos")
    @Operation(summary = "Buscar aluguéis ativos", description = "Retorna uma lista de todos os aluguéis ativos")
    public ResponseEntity<Page<Aluguel>> buscarAluguelPorAtivos(
        @RequestParam(defaultValue = "0") int pagina, 
        @RequestParam(defaultValue = "10") int tamanho
    ) {
        Page<Aluguel> alugueisAtivos = aluguelService.buscarAlugueisAtivos(pagina, tamanho);
        return ResponseEntity.ok(alugueisAtivos);
    }

    @GetMapping("/alugueis/finalizados")
    @Operation(summary = "Buscar aluguéis finalizados", description = "Retorna uma lista de todos os aluguéis finalizados")
    public ResponseEntity<Page<Aluguel>> buscarAlugueisFinalizados(
        @RequestParam(defaultValue = "0") int pagina, 
        @RequestParam(defaultValue = "10") int tamanho
    ) {
        Page<Aluguel> alugueisFinalizados = aluguelService.buscarAlugueisFinalizados(pagina, tamanho);
        return ResponseEntity.ok(alugueisFinalizados);
    }
}