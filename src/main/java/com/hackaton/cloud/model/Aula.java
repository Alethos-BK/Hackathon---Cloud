package com.hackaton.cloud.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "aula")
public class Aula {
    @Id
    @ApiModelProperty(value = "Id usuario (fk)")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_usuario")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataAula;

    @Column(nullable = false)
    private  String urlVideo;

    @Column(nullable = false)
    private String arquivoExercicio;

    @Column(nullable = false)
    private String arquivoMateria;

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
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }   

    public LocalDate getDataAula() {
        return dataAula;
    }

    public void setDataAula(LocalDate dataAula) {
        this.dataAula = dataAula;
    }   
    
    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getArquivoExercicio() {
        return arquivoExercicio;
    }

    public void setArquivoExercicio(String arquivoExercicio) {
        this.arquivoExercicio = arquivoExercicio;
    }
    
    public String getArquivoMateria() {
        return arquivoMateria;
    }

    public void setArquivoMateria(String arquivoMateria) {
        this.arquivoMateria = arquivoMateria;
    }
}
