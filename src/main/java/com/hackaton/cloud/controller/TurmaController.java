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

import com.hackaton.cloud.model.AlunoETurma;
import com.hackaton.cloud.model.Turma;
import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.service.AlunoETurmaService;
import com.hackaton.cloud.service.TurmaService;
import com.hackaton.cloud.shared.TurmaDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/turmas")
@Api
public class TurmaController {
    @Autowired
    TurmaService _TurmaService;

    @Autowired
    AlunoETurmaService _alunoETurmaService;

    @ApiOperation(value = "Retorna todos as turmas :)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de turmas encontrada com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe turma cadastrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping()
    public ResponseEntity<Page<Turma>> obterTodos(@PageableDefault(page=0, size=10) Pageable pageable) {
        if( _TurmaService.obterTodos(pageable).isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_TurmaService.obterTodos(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna turma por id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Turma encontrado com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe usuario cadastrado com esse id:("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turma>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Turma> usuario = _TurmaService.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona nova turma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Turma cadastrado com sucessp :)"),
        @ApiResponse(code = 400, message = "Amigo, revisa ai teus parametros por favor :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping("/{idProfessor}")
    public ResponseEntity<Turma> adicionarTurma(@PathVariable(value = "idProfessor") Long idProfessor, @RequestBody TurmaDto Turma) {
        Turma novoTurma = _TurmaService.adicionarTurma(idProfessor, Turma);
        return new ResponseEntity<>(novoTurma, HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza Turma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Turma atualizado com sucessp :)"),
        @ApiResponse(code = 400, message = "Amigo, revisa ai teus parametros por favor :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable(value = "id") Long id, @Valid @RequestBody TurmaDto Turma) {
        return new ResponseEntity<>(_TurmaService.atualizarTurma(id, Turma), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta turma por id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Turma deletado com sucesso :)"),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _TurmaService.deletarTurma(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Obtem todos os alunos da turma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de alunos da turma encontrada :)"),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("idTurma/{idTurma}")
    public ResponseEntity<List<Optional<Usuario>>> obterUsuariosDaTurma(@PathVariable(value = "idTurma") Long idTurma) {
        List<Optional<Usuario>> usuarios = _alunoETurmaService.obterUsuariosDaTurma(idTurma);
        
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @ApiOperation(value = "Faz ligação de aluno e turma")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso :)"),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping("/{idTurma}/{idAluno}")
    public ResponseEntity<Void> adicionarLigacaoTurmaAluno(@PathVariable(value = "idTurma") Long idTurma, @PathVariable(value = "idAluno") Long idAluno) {
        _alunoETurmaService.adicionarAluno(idAluno, idTurma);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
