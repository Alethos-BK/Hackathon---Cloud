package com.hackaton.cloud.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hackaton.cloud.exception.NotFoundException;
import com.hackaton.cloud.model.Turma;
import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.repository.TurmaRepository;
import com.hackaton.cloud.shared.TurmaDto;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository _turmaRepository;

    @Autowired
    private UsuarioService _usuarioService;

    @Override
    public Page<Turma> obterTodos(Pageable pageable) {
        return this._turmaRepository.findAll(pageable);
    }

    @Override
    public Optional<Turma> obterPorId(Long id) {
        Optional<Turma> encontrado = this._turmaRepository.findById(id);

		if (!encontrado.isPresent()) {
			throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
		}
		return encontrado;
    }

    @Override
    public Turma adicionarTurma(Long idProfessor, TurmaDto turma) {
        Optional<Usuario> usuario = _usuarioService.obterPorId(idProfessor);
        
        ModelMapper mapper = new ModelMapper();
        Turma novaTurma = mapper.map(turma, Turma.class);
        novaTurma.setProfessor(usuario.get());
        return this._turmaRepository.save(novaTurma);    
    }

    @Override
    public Turma atualizarTurma(Long id, TurmaDto Turma) {
        Optional<Turma> TurmaAntiga = obterPorId(id);
        ModelMapper mapper = new ModelMapper();

        Turma TurmaAtualizado = mapper.map(Turma, Turma.class);
        TurmaAtualizado.setId(id);

        return this._turmaRepository.save(TurmaAtualizado);
    }

    @Override
    public void deletarTurma(Long id) {
        Optional<Turma> Turma = obterPorId(id);

		if (!Turma.isPresent()) {
			throw new NotFoundException("Não existe equipe com o id informado: " + id);
		}

		this._turmaRepository.deleteById(id);
    }
    
}
