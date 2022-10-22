package com.hackaton.cloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    
    public BadRequestException() {
        super("Recurso fora dos parâmetros :(");
    }

    public BadRequestException(String mensagem) {
        super(mensagem);
    }
}
