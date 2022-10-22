package com.hackaton.cloud.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.hackaton.cloud.model.Usuario;

public interface UsuarioService {
    Page<Usuario> obterTodos(Pageable pageable);
}
