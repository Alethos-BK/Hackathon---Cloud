package com.hackaton.cloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackaton.cloud.model.AlunoETurma;

@Repository
public interface AlunoETurmaRepository  extends JpaRepository<AlunoETurma, Long>{
    List<AlunoETurma> findByIdTurma(Long idTurma); 
    List<AlunoETurma> findByIdAluno(Long idAluno); 
}
