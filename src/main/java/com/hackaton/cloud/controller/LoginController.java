package com.hackaton.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.cloud.service.UsuarioService;
import com.hackaton.cloud.shared.login.LoginRequest;
import com.hackaton.cloud.shared.login.LoginResponse;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api
@RestController
@RequestMapping("/api/login")
public class LoginController {
    
    @Autowired
    private UsuarioService _usuarioService;
    
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        return new ResponseEntity<>(_usuarioService.logar(request.getLogin(), request.getSenha()), HttpStatus.OK);
    }
}
