package com.hackaton.cloud.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.service.UsuarioService;
import com.hackaton.cloud.shared.UsuarioDtoCadastro;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/usuarios")
@Api
public class UsuarioController {
    
    @Autowired
    UsuarioService _usuarioService;

    @GetMapping()
    public ResponseEntity<Page<Usuario>> obterTodos(@PageableDefault(page=0, size=10) Pageable pageable) {
        if( _usuarioService.obterTodos(pageable).isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_usuarioService.obterTodos(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = _usuarioService.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarCoordenador(@Valid UsuarioDtoCadastro usuario) {
        Usuario novousuario = _usuarioService.adicionarUsuario(usuario);
        return new ResponseEntity<>(novousuario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody @Valid UsuarioDtoCadastro usuario) {
        return new ResponseEntity<>(_usuarioService.atualizarUsuario(id, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _usuarioService.deletarUsuario(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
