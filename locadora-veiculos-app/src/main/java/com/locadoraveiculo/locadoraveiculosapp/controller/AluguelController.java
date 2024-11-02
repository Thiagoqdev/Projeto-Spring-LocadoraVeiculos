package com.locadoraveiculo.locadoraveiculosapp.controller;


import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.service.AluguelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    @Autowired
    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    public ResponseEntity<Aluguel>CriarAluguel(@RequestBody Aluguel aluguel) {
        Aluguel novoAluguel = aluguelService.CriarAluguel(aluguel);
        return new ResponseEntity<>(novoAluguel, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Aluguel>>listarAlugueis(){
        List<Aluguel> aluguels = aluguelService.listarAluguels();
        return ResponseEntity.ok(aluguels);
    }


    @GetMapping("/codigo")
    public ResponseEntity<Aluguel>buscarAluguelPorCodigo(@PathVariable Long codigo){
        return aluguelService.buscarAluguelPorcodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Aluguel> atualizarAluguel(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        try {
            Aluguel aluguelAtualizado = aluguelService.atualizarAluguel(id, aluguel);
            return ResponseEntity.ok(aluguelAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerAluguel(@PathVariable Long codigo){
        aluguelService.deletarAluguel(codigo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{codigo}/finalizar")
    public ResponseEntity<Aluguel> finalizarAluguel(@PathVariable Long codigo){
        Aluguel aluguelfinalizado = aluguelService.finalizarAluguel(codigo);
        return ResponseEntity.ok(aluguelfinalizado);
    }

    @GetMapping("/alugueis/ativos")
    public ResponseEntity<List<Aluguel>>BuscarAluguelPorAtivos(){
        List<Aluguel>alugueisAtivos = aluguelService.buscarAlugueisAtivos();
        return ResponseEntity.ok(alugueisAtivos);
    }

    @GetMapping("alugueis/finalizados")
    public ResponseEntity<List<Aluguel>>BuscarAlugueisFinalizados(){
        List<Aluguel> alugueisFinalizados = aluguelService.buscarAlugueisFinalizados();
        return ResponseEntity.ok(alugueisFinalizados);
    }

}
