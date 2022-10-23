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
import org.springframework.web.bind.annotation.RequestBody;


import com.hackaton.cloud.model.Aula;
import com.hackaton.cloud.service.AulaService;
import com.hackaton.cloud.shared.AulaDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/aulas")
@Api
public class AulaController {

    @Autowired
    AulaService _aulaService;

    @ApiOperation(value = "Retorna todos as aulas cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de aluas encontrada com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe usuario cadastrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping()
    public ResponseEntity<Page<Aula>> obterTodos(@PageableDefault(page=0, size=10) Pageable pageable) {
        if( _aulaService.obterTodos(pageable).isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_aulaService.obterTodos(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna aula por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Aula encontrada com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe aula cadastrada com esse id:("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aula>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Aula> usuario = _aulaService.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona nova aula")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Aula cadastrada com sucessp :)"),
        @ApiResponse(code = 400, message = "Amigo, revisa ai teus parametros por favor :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping("/{idTurma}")
    public ResponseEntity<Aula> adicionarAula(@PathVariable(value = "idTurma") Long idTurma, @RequestBody AulaDto Aula) {
        Aula novoAula = _aulaService.adicionarAula(idTurma, Aula);
        return new ResponseEntity<>(novoAula, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza Aula")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Aula atualizada com sucessp :)"),
        @ApiResponse(code = 400, message = "Amigo, revisa ai teus parametros por favor :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PutMapping("/{id}")
    public ResponseEntity<Aula> atualizar(@PathVariable(value = "id") Long id, @Valid @RequestBody AulaDto Aula) {
        return new ResponseEntity<>(_aulaService.atualizarAula(id, Aula), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta aula por id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Aula deletado com sucesso :)"),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _aulaService.deletarAula(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
