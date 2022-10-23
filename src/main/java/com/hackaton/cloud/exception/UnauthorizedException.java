package com.hackaton.cloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException() {
		super("Usuario não está autorizado a utilizar esse recurso");
	}

	public UnauthorizedException(String mensagem) {
		super(mensagem);
	}
}
