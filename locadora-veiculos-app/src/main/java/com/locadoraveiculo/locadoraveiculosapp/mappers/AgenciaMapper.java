package com.locadoraveiculo.locadoraveiculosapp.mappers;

import com.locadoraveiculo.locadoraveiculosapp.model.Agencia;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AgenciaMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void atualizarAgencia(Agencia agenciaAtualizada, @MappingTarget Agencia agenciaExistente);
}
