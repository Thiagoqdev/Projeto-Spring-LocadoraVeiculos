package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.repository.AluguelRepository;
import com.locadoraveiculo.locadoraveiculosapp.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;


    @Autowired
    public AluguelService(AluguelRepository aluguelRepository, VeiculoRepository veiculoRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
    }


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

    public List<Aluguel> listarAluguels() {
        return aluguelRepository.findAll();
    }

    public Optional<Aluguel> buscarAluguelPorcodigo(Long codigo) {
        return aluguelRepository.findById(codigo);
    }

    public Aluguel atualizarAluguel(Long codigo, Aluguel aluguelAtualizado) {
        return aluguelRepository.findById(codigo)
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
                .orElseThrow(() -> new EntityNotFoundException("Aluguel não encontrado com id: " + codigo));
    }

    @Transactional
    public void deletarAluguel(Long codigo) {
        Aluguel aluguel = aluguelRepository.findById(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Aluguel nao encontrado" + codigo));

        Veiculo veiculo = aluguel.getVeiculo();
        if (veiculo != null) {
            veiculo.setStatusAluguel(Veiculo.StatusAluguel.DISPONIVEL);
            veiculoRepository.save(veiculo);
        }
        aluguelRepository.deleteById(codigo);
    }


    @Transactional
    public Aluguel finalizarAluguel(Long codigo) {
        return aluguelRepository.findById(codigo)
                .map(aluguel -> {
                    aluguel.setDataFim(new Date());
                    Veiculo veiculo = aluguel.getVeiculo();
                    if (veiculo != null) {
                        veiculo.setStatusAluguel(Veiculo.StatusAluguel.DISPONIVEL);
                        veiculoRepository.save(veiculo);
                    }
                    return aluguelRepository.save(aluguel);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aluguel nao encontrado com id" + codigo));
    }


    public List<Aluguel> buscarAlugueisAtivos() {
        return aluguelRepository.findAll().stream()
                .filter(aluguel -> aluguel.getDataInicio() == null)
                .toList();
    }

    public List<Aluguel> buscarAlugueisFinalizados() {
        return aluguelRepository.findAll().stream()
                .filter(aluguel -> aluguel.getDataFim() != null)
                .toList();
    }

}
