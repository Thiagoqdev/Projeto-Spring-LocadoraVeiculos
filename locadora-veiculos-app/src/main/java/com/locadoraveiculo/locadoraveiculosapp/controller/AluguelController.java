package com.locadoraveiculo.locadoraveiculosapp.controller;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Aluguel CriarAluguel(@RequestBody Aluguel aluguel) {
        return aluguelService.CriarAluguel(aluguel);
    }

    @GetMapping
    @Operation(summary = "Listar aluguéis", description = "Retorna uma lista de todos os aluguéis")
    public Page<Aluguel> listarAlugueis(@RequestParam int pagina, @RequestParam int tamanho) {
        return aluguelService.listarAluguels(pagina, tamanho);
    }

    @GetMapping("/aluguel_id")
    @Operation(summary = "Buscar aluguel por ID", description = "Retorna um aluguel específico pelo seu ID")
    public Aluguel buscarAluguelPorCodigo(@Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id) {
        return aluguelService.buscarAluguelPorcodigo(aluguel_id);
    }

    @PutMapping("/{aluguel_id}")
    @Operation(summary = "Atualizar aluguel", description = "Atualiza os dados de um aluguel existente")
    public ResponseEntity<Aluguel> atualizarAluguel(@Parameter(description = "ID do aluguel") @PathVariable Long veiculo_id, @RequestBody Aluguel aluguel) {
        Aluguel aluguelAtualizado = aluguelService.atualizarAluguel(veiculo_id, aluguel);
        return ResponseEntity.ok().body(aluguelAtualizado);
    }

    @DeleteMapping("/{aluguel_id}")
    @Operation(summary = "Remover aluguel", description = "Remove um aluguel do sistema")
    public void removerAluguel(@Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id) {
        aluguelService.deletarAluguel(aluguel_id);
    }

    @PostMapping("/{aluguel_id}/finalizar")
    @Operation(summary = "Finalizar aluguel", description = "Finaliza um aluguel em andamento")
    public ResponseEntity<Aluguel> finalizarAluguel(@Parameter(description = "ID do aluguel") @PathVariable Long aluguel_id) {
        Aluguel aluguelfinalizado = aluguelService.finalizarAluguel(aluguel_id);
        return ResponseEntity.ok().body(aluguelfinalizado);
    }

    @GetMapping("/alugueis/ativos")
    @Operation(summary = "Buscar aluguéis ativos", description = "Retorna uma lista de todos os aluguéis ativos")
    public Page<Aluguel> BuscarAluguelPorAtivos(@RequestParam int pagina, @RequestParam int tamanho) {
        return aluguelService.buscarAlugueisAtivos(pagina, tamanho);
    }

    @GetMapping("alugueis/finalizados")
    @Operation(summary = "Buscar aluguéis finalizados", description = "Retorna uma lista de todos os aluguéis finalizados")
    public Page<Aluguel> BuscarAlugueisFinalizados(@RequestParam int pagina, @RequestParam int tamanho) {
        return aluguelService.buscarAlugueisFinalizados(pagina, tamanho);
    }
}