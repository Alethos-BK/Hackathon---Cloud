package com.hackaton.cloud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackaton.cloud.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
    Optional<Aula> findById(Long id); 
    Optional<Aula> findByNome(String nome);
}
