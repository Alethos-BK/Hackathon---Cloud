package com.hackaton.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository _usuarioRepository;
    
    @Override
    public Page<Usuario> obterTodos(Pageable pageable) {
        return this._usuarioRepository.findAll(pageable);
    }
}
