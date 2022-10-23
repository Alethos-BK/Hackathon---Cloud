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

@RestController
@RequestMapping("/api/turmas")
@Api
public class TurmaController {
    @Autowired
    TurmaService _TurmaService;

    @Autowired
    AlunoETurmaService _alunoETurmaService;

    @GetMapping()
    public ResponseEntity<Page<Turma>> obterTodos(@PageableDefault(page=0, size=10) Pageable pageable) {
        if( _TurmaService.obterTodos(pageable).isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(_TurmaService.obterTodos(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turma>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Turma> usuario = _TurmaService.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/{idProfessor}")
    public ResponseEntity<Turma> adicionarTurma(@PathVariable(value = "idProfessor") Long idProfessor, @RequestBody TurmaDto Turma) {
        Turma novoTurma = _TurmaService.adicionarTurma(idProfessor, Turma);
        return new ResponseEntity<>(novoTurma, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable(value = "id") Long id, @Valid @RequestBody TurmaDto Turma) {
        return new ResponseEntity<>(_TurmaService.atualizarTurma(id, Turma), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _TurmaService.deletarTurma(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("idTurma/{idTurma}")
    public ResponseEntity<List<Optional<Usuario>>> obterUsuariosDaTurma(@PathVariable(value = "idTurma") Long idTurma) {
        List<Optional<Usuario>> usuarios = _alunoETurmaService.obterUsuariosDaTurma(idTurma);
        
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping("/{idTurma}/{idAluno}")
    public ResponseEntity<Void> adicionarTurma(@PathVariable(value = "idTurma") Long idTurma, @PathVariable(value = "idAluno") Long idAluno) {
        _alunoETurmaService.adicionarAluno(idAluno, idTurma);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
