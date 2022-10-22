package com.hackaton.cloud.repository;

import org.springframework.stereotype.Repository;

import com.hackaton.cloud.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
