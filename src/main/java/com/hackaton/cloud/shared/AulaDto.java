package com.hackaton.cloud.shared;

import java.time.LocalDate;

public class AulaDto {
    private String nome;

    private String descricao;
    
    private LocalDate dataAula;

    private  String urlVideo;

    private  String arquivoExercicio;

    private String arquivoMateria;

    public String getArquivoMateria() {
        return arquivoMateria;
    }

    public void setArquivoMateria(String arquivoMateria) {
        this.arquivoMateria = arquivoMateria;
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

}
