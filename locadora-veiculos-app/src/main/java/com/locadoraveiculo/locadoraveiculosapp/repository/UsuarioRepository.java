package com.locadoraveiculo.locadoraveiculosapp.repository;


import com.locadoraveiculo.locadoraveiculosapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
