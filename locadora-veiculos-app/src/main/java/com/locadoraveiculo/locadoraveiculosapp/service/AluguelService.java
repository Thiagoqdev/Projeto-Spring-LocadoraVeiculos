package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.mappers.AluguelMapper;
import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.repository.AluguelRepository;
import com.locadoraveiculo.locadoraveiculosapp.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private AluguelMapper aluguelMapper;


    @Transactional
    public Aluguel CriarAluguel(Aluguel aluguel) {
        Veiculo veiculo = aluguel.getVeiculo();
        if (veiculo != null && veiculo.getStatusAluguel() == Veiculo.StatusAluguel.DISPONIVEL) {
            veiculo.setStatusAluguel(Veiculo.StatusAluguel.ALUGADO);
            veiculoRepository.save(veiculo);
            return aluguelRepository.save(aluguel);
        } else {
            throw new IllegalStateException("Veiculo nao está disponivel para alugar");
        }
    }

    public Page<Aluguel> listarAluguels(int pagina, int tamanho){
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("aluguel_id").ascending());
        return aluguelRepository.findAll(pageable);
    }

    public Aluguel buscarAluguelPorcodigo(Long aluguel_id) {
        return aluguelRepository.findById(aluguel_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel com id " + aluguel_id + " não foi encontrado"));
    }

    public Aluguel atualizarAluguel(Long aluguel_id, Aluguel aluguelAtualizado) {
        return aluguelRepository.findById(aluguel_id)
                .map(aluguelExistente -> {
                    aluguelExistente.setValorCobrado(aluguelAtualizado.getValorCobrado());
                    aluguelExistente.setDataInicio(aluguelAtualizado.getDataInicio());
                    aluguelExistente.setDataFim(aluguelAtualizado.getDataFim());
                    aluguelExistente.setTipoPagamento(aluguelAtualizado.getTipoPagamento());

                    // Verifica se o veículo foi alterado
                    if (!aluguelExistente.getVeiculo().equals(aluguelAtualizado.getVeiculo())) {
                        Veiculo veiculoAntigo = aluguelExistente.getVeiculo();
                        Veiculo veiculoNovo = aluguelAtualizado.getVeiculo();

                        // Libera o veículo antigo
                        if (veiculoAntigo != null) {
                            veiculoAntigo.setStatusAluguel(Veiculo.StatusAluguel.DISPONIVEL);
                            veiculoRepository.save(veiculoAntigo);
                        }

                        // Verifica e atualiza o novo veículo
                        if (veiculoNovo != null) {
                            if (veiculoNovo.getStatusAluguel() == Veiculo.StatusAluguel.DISPONIVEL) {
                                veiculoNovo.setStatusAluguel(Veiculo.StatusAluguel.ALUGADO);
                                veiculoRepository.save(veiculoNovo);
                                aluguelExistente.setVeiculo(veiculoNovo);
                            } else {
                                throw new IllegalStateException("Novo veículo não está disponível para aluguel.");
                            }
                        } else {
                            throw new IllegalArgumentException("Veículo não pode ser nulo.");
                        }
                    }

                    return aluguelRepository.save(aluguelExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aluguel não encontrado com veiculo_id: " + aluguel_id));
    }

    @Transactional
    public void deletarAluguel(Long aluguel_id) {
        Aluguel aluguelExistente = buscarAluguelPorcodigo(aluguel_id);
        aluguelRepository.delete(aluguelExistente);
    }


    @Transactional
    public Aluguel finalizarAluguel(Long aluguel_id) {
        return aluguelRepository.findById(aluguel_id)
                .map(aluguel -> {
                    aluguel.setDataFim(new Date());
                    Veiculo veiculo = aluguel.getVeiculo();
                    if (veiculo != null) {
                        veiculo.setStatusAluguel(Veiculo.StatusAluguel.DISPONIVEL);
                        veiculoRepository.save(veiculo);
                    }
                    return aluguelRepository.save(aluguel);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aluguel nao encontrado com veiculo_id" + aluguel_id));
    }


    public Page<Aluguel> buscarAlugueisAtivos(int pagina, int tamanho){
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("aluguel_id").ascending());
        return aluguelRepository.findAll(pageable);
    }

    public Page<Aluguel> buscarAlugueisFinalizados(int pagina, int tamanho){
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("aluguel_id").ascending());
        return aluguelRepository.findAll(pageable);
    }

}
