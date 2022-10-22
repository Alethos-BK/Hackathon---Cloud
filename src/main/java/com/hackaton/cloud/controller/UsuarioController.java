package com.hackaton.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService _usuarioService;

    @GetMapping()
    public ResponseEntity<Page<Usuario>> obterTodos(@PageableDefault(page=0, size=4) Pageable pageable) {
        if( _usuarioService.obterTodos(pageable).isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_usuarioService.obterTodos(pageable), HttpStatus.OK);
    }

}
