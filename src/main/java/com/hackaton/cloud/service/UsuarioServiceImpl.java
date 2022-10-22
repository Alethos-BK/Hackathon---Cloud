package com.hackaton.cloud.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hackaton.cloud.exception.BadRequestException;
import com.hackaton.cloud.exception.NotFoundException;
import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.repository.UsuarioRepository;
import com.hackaton.cloud.shared.UsuarioDtoCadastro;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository _usuarioRepository;
    
    @Override
    public Page<Usuario> obterTodos(Pageable pageable) {
        return this._usuarioRepository.findAll(pageable);
    }

    @Override
	public Optional<Usuario> obterPorId(Long id) {
		Optional<Usuario> encontrado = this._usuarioRepository.findById(id);

		if (!encontrado.isPresent()) {
			throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
		}
		return encontrado;
	}

    @Override
    public Usuario adicionarCoordenador(UsuarioDtoCadastro usuarioDto) {
        ModelMapper mapper = new ModelMapper();

        Usuario novoUsuario = mapper.map(usuarioDto, Usuario.class);
        Optional<Usuario> usuarioProcurado = this._usuarioRepository.findByEmail(novoUsuario.getEmail());

        if (usuarioProcurado.isPresent()) {
            throw new BadRequestException("Usuário com o email: " + novoUsuario.getEmail() + " já encontrado :(");
        }

        if (novoUsuario.getStatus() == null) {
            novoUsuario.setStatus("");
        }
        novoUsuario.setMatricula(Long.parseLong(gerarMatricula()));
    
        return this._usuarioRepository.save(novoUsuario);    
    }

    public String gerarMatricula() {
        String matricula = "";

       LocalDateTime data = LocalDateTime.now();
       Instant horaAgora = Instant.now();

       matricula = matricula
            + data.getYear()
            + data.getMonthValue()
            + data.getDayOfYear()
            + data.getHour()
            + data.getMinute()
            + data.getSecond();


        return matricula;
    }

}
