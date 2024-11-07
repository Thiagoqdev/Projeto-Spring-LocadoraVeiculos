package com.locadoraveiculo.locadoraveiculosapp.service;


import com.locadoraveiculo.locadoraveiculosapp.mappers.AgenciaMapper;
import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import com.locadoraveiculo.locadoraveiculosapp.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private AgenciaMapper agenciaMapper;

    public Agencia criarAgencia(Agencia agencia) {
        agenciaRepository.save(agencia);
        return agencia;
    }

    public Page<Agencia> listarAgencias(int pagina, int tamanho){
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("numeroAgencia").ascending());
        return agenciaRepository.findAll(pageable);
    }

    public Agencia buscarAgenciaPorId(Long numeroAgencia) {
        return agenciaRepository.findById(numeroAgencia)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agência com número " + numeroAgencia + " não foi encontrado"));
    }

    public Agencia buscarAgenciaPorCodigo(Long numeroAgencia) {
        return buscarAgenciaPorId(numeroAgencia);
    }

    public Agencia atualizarAgencia(Long numeroAgencia, Agencia agenciaAtualizada) {
        Agencia agenciaExistente = buscarAgenciaPorId(numeroAgencia);
        agenciaMapper.atualizarAgencia(agenciaAtualizada, agenciaExistente);
        return agenciaRepository.save(agenciaExistente);
    }

    public void removerAgencia(Long numeroAgencia) {
        Agencia produtoExistente = buscarAgenciaPorId(numeroAgencia);
        agenciaRepository.delete(produtoExistente);
    }
}
