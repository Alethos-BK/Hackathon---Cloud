package com.hackaton.cloud.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hackaton.cloud.model.Aula;
import com.hackaton.cloud.shared.AulaDto;

public interface AulaService {
    Page<Aula> obterTodos(Pageable pageable);
    Optional<Aula> obterPorId(Long id);
    Aula adicionarAula(AulaDto aula);
    Aula atualizarAula(Long id, AulaDto aula);
    void deletarAula(Long id);
}
