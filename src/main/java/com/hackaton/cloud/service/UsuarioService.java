package com.hackaton.cloud.service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.shared.UsuarioDtoCadastro;

public interface UsuarioService {
    Page<Usuario> obterTodos(Pageable pageable);
    Optional<Usuario> obterPorId(Long id);
    Usuario adicionarCoordenador(UsuarioDtoCadastro usuarioDto);

}
