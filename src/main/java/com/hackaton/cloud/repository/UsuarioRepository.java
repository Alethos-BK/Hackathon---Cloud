package com.hackaton.cloud.repository;

import org.springframework.stereotype.Repository;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.shared.TipoUsuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findById(Long id); 
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByNomeContaining(String nome);
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}
