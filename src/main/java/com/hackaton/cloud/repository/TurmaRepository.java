package com.hackaton.cloud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackaton.cloud.model.Turma;

public interface TurmaRepository  extends JpaRepository<Turma, Long> {
    Optional<Turma> findById(Long id); 
    Optional<Turma> findByNome(String nome);
}
    
