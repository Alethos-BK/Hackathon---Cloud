package com.hackaton.cloud.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "turma")
public class Turma {
    @Id
    @ApiModelProperty(value = "Id usuario (fk)")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_usuario")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario professor;

    @OneToMany()
    private List<Aula> aulas;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
       this.professor = professor;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
}
