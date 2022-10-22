package com.hackaton.cloud.shared;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDtoCadastro {
    private String nome;
    
    @NotBlank(message = "Campo não informado!")
    @Email(message = "Email inválido!")
    private String email;
    
    @NotBlank(message = "Campo não informado!")
    @Size(min = 6, max = 6, message = "Senha deve ter 6 caracteres!")
    private String senha;
    
    private TipoUsuario tipoUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
