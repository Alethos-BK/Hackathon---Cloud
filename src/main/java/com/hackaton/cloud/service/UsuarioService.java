package com.hackaton.cloud.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.shared.UsuarioDtoCadastro;

public interface UsuarioService {
    Page<Usuario> obterTodos(Pageable pageable);
    Optional<Usuario> obterPorId(Long id);
    Usuario adicionarUsuario(UsuarioDtoCadastro usuarioDto);
    Usuario atualizarUsuario(Long id, UsuarioDtoCadastro usuario);
    void deletarUsuario(Long id);
    List<Usuario> obterTodosAlunos();
    List<Usuario> obterTodosProfessores();
}
