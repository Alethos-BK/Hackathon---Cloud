package com.hackaton.cloud.shared;

public enum TipoUsuario {
    ALUNO("aluno"),
    PROFESSOR("professor"),
    COORDENADOR("coordenador");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
