package com.hackaton.cloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackaton.cloud.model.AlunoETurma;
import com.hackaton.cloud.model.Turma;
import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.repository.AlunoETurmaRepository;

@Service
public class AlunoETurmaService {
    @Autowired
    private AlunoETurmaRepository _alunoETurmaRepository;

    @Autowired
    private UsuarioService _usuarioService;

    @Autowired
    private TurmaService _turmaService;

    public void adicionarAluno(Long idAluno, Long idTurma) {
        Optional<Turma> turma = _turmaService.obterPorId(idTurma);
        Optional<Usuario> aluno = _usuarioService.obterPorId(idAluno);

        AlunoETurma alunoETurma = new AlunoETurma();

        alunoETurma.setIdAluno(aluno.get().getId());
        alunoETurma.setIdTurma(turma.get().getId());

        _alunoETurmaRepository.save(alunoETurma);
    }

    public List<Optional<Usuario>> obterUsuariosDaTurma(Long idTurma) {
        List<AlunoETurma> alunosTurma = _alunoETurmaRepository.findByIdTurma(idTurma);
        List<Optional<Usuario>> alunos = new ArrayList();

        for (AlunoETurma aluno : alunosTurma) {
            alunos.add(_usuarioService.obterPorId(aluno.getIdAluno()));
        }

        return alunos;
    }
}
