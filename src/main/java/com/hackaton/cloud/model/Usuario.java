package com.hackaton.cloud.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackaton.cloud.shared.TipoUsuario;

import io.swagger.annotations.ApiModelProperty;

//import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @ApiModelProperty(value = "Id usuario (fk)")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_usuario")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Email
    @NotBlank(message = "não é um email válido!")
    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    @Column(unique = true, nullable = false)
    private Long matricula;

    @Column(nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(nullable = false)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getMatricula(){
        return matricula;
    }

    public void setMatricula(Long matricula){
        this.matricula = matricula;
    }

    public TipoUsuario getTipoUsuario(){
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario){
        this.tipoUsuario = tipoUsuario;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
