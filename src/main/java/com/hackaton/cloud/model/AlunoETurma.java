package com.hackaton.cloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "aluno_turma")
public class AlunoETurma {
    @Id
    @ApiModelProperty(value = "Id usuario (fk)")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_usuario")
    private Long id;

    @Column(nullable = false)
    public Long idAluno;

    @Column(nullable = false)
    public Long idTurma;

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

}
