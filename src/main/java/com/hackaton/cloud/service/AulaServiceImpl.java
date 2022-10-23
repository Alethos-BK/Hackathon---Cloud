package com.hackaton.cloud.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hackaton.cloud.controller.TurmaController;
import com.hackaton.cloud.exception.NotFoundException;
import com.hackaton.cloud.model.Aula;
import com.hackaton.cloud.model.Turma;
import com.hackaton.cloud.repository.AulaRepository;
import com.hackaton.cloud.shared.AulaDto;

@Service
public class AulaServiceImpl implements AulaService {

    @Autowired
    private AulaRepository _aulaRepository;

    @Autowired
    private TurmaService _turmaService;

    @Override
    public Page<Aula> obterTodos(Pageable pageable) {
        return this._aulaRepository.findAll(pageable);
    }

    @Override
    public Optional<Aula> obterPorId(Long id) {
        Optional<Aula> encontrado = this._aulaRepository.findById(id);

		if (!encontrado.isPresent()) {
			throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
		}
		return encontrado;
    }

    @Override
    public Aula adicionarAula(Long idTurma,AulaDto aula) {
        Optional<Turma> turma = _turmaService.obterPorId(idTurma);

        ModelMapper mapper = new ModelMapper();
        Aula novaAula = mapper.map(aula, Aula.class);
        novaAula.setTurma(turma.get());

        return this._aulaRepository.save(novaAula);    
    }

    @Override
    public Aula atualizarAula(Long id, AulaDto aula) {
        Optional<Aula> aulaAntiga = obterPorId(id);
        ModelMapper mapper = new ModelMapper();

        Aula aulaAtualizado = mapper.map(aula, Aula.class);
        aulaAtualizado.setId(id);

        return this._aulaRepository.save(aulaAtualizado);
    }

    @Override
    public void deletarAula(Long id) {
        Optional<Aula> aula = obterPorId(id);

		if (!aula.isPresent()) {
			throw new NotFoundException("Não existe equipe com o id informado: " + id);
		}

		this._aulaRepository.deleteById(id);
    }
    
}
