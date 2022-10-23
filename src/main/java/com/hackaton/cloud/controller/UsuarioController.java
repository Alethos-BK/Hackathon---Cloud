package com.hackaton.cloud.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.service.UsuarioService;
import com.hackaton.cloud.shared.UsuarioDtoCadastro;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/usuarios")
@Api("Aplicação Cloud Learn")
public class UsuarioController {
    
    @Autowired
    UsuarioService _usuarioService;

    @ApiOperation(value = "Retorna todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de usuarios encontrada com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe usuario cadastrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping()
    public ResponseEntity<Page<Usuario>> obterTodos(@PageableDefault(page=0, size=100) Pageable pageable) {
        if( _usuarioService.obterTodos(pageable).isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_usuarioService.obterTodos(pageable), HttpStatus.OK);
    }

    
    @ApiOperation(value = "Retorna usuário por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário encontrado com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe usuario cadastrado com esse id:("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = _usuarioService.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    
    @ApiOperation(value = "Retorna todos os alunos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de alunos encontrada com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe aluno cadastrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/todosAlunos")
    public ResponseEntity<List<Usuario>> obterTodosAlunos() {
        List<Usuario> alunos = _usuarioService.obterTodosAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna todos os professores cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de professores encontrado com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe professor cadastrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/todosProfessores")
    public ResponseEntity<List<Usuario>> obterTodosProfessores() {
        List<Usuario> alunos = _usuarioService.obterTodosProfessores();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona novo usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário cadastrado com sucessp :)"),
        @ApiResponse(code = 400, message = "Amigo, revisa ai teus parametros por favor :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@Valid @RequestBody UsuarioDtoCadastro usuario) {
        Usuario novousuario = _usuarioService.adicionarUsuario(usuario);
        return new ResponseEntity<>(novousuario, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza Usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário atualizado com sucessp :)"),
        @ApiResponse(code = 400, message = "Amigo, revisa ai teus parametros por favor :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody @Valid UsuarioDtoCadastro usuario) {
        return new ResponseEntity<>(_usuarioService.atualizarUsuario(id, usuario), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta usuario por id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Usuário deletado com sucesso :)"),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _usuarioService.deletarUsuario(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
