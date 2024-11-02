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
        if (veiculo == null && veiculo.getStatusAluguel() == Veiculo.StatusAluguel.DISPONIVEL) {
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

    public Optional<Aluguel> buscarAluguelPorId(Long id) {
        return aluguelRepository.findById(id);
    }

    @Transactional
    public void deletarAluguel(Long id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluguel nao encontrado" + id));

        Veiculo veiculo = aluguel.getVeiculo();
        if (veiculo != null) {
            veiculo.setStatusAluguel(Veiculo.StatusAluguel.DISPONIVEL);
            veiculoRepository.save(veiculo);
        }
        aluguelRepository.deleteById(id);
    }


    @Transactional
    public Aluguel finalizarAluguel(Long id) {
        return aluguelRepository.findById(id)
                .map(aluguel -> {
                    aluguel.setDataFim(new Date());
                    Veiculo veiculo = aluguel.getVeiculo();
                    if (veiculo != null) {
                        veiculo.setStatusAluguel(Veiculo.StatusAluguel.DISPONIVEL);
                        veiculoRepository.save(veiculo);
                    }
                    return aluguelRepository.save(aluguel);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aluguel nao encontrado com id" + id));
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
