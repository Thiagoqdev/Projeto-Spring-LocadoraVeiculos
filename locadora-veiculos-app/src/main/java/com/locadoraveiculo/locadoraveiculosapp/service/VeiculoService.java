package com.locadoraveiculo.locadoraveiculosapp.service;

import com.locadoraveiculo.locadoraveiculosapp.model.Veiculo;
import com.locadoraveiculo.locadoraveiculosapp.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;


    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                   veiculo.setMarca(veiculoAtualizado.getMarca());
                   veiculo.setModelo(veiculoAtualizado.getModelo());
                   veiculo.setAnoFabricacao(veiculoAtualizado.getAnoFabricacao());
                   return veiculoRepository.save(veiculo);
                        })
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com id: " + id));
    }

    public void deletarVeiculo(Long id) {
        veiculoRepository.deleteById(id);
    }
}
