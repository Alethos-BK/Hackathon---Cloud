package com.hackaton.cloud.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hackaton.cloud.model.Turma;
import com.hackaton.cloud.shared.TurmaDto;

public interface TurmaService {
    Page<Turma> obterTodos(Pageable pageable);
    Optional<Turma> obterPorId(Long id);
    Turma adicionarTurma(Long idProfessor, TurmaDto aula);
    Turma atualizarTurma(Long id, TurmaDto aula);
    void deletarTurma(Long id);
}
