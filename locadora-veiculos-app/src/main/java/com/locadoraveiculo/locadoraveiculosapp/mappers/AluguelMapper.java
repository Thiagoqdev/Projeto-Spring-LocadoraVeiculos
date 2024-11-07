package com.locadoraveiculo.locadoraveiculosapp.mappers;

import com.locadoraveiculo.locadoraveiculosapp.model.Aluguel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AluguelMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarAluguel(Aluguel aluguelAtualizado, @MappingTarget Aluguel aluguelExistente);
}