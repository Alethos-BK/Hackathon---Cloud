package com.hackaton.cloud.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hackaton.cloud.exception.BadRequestException;
import com.hackaton.cloud.exception.NotFoundException;
import com.hackaton.cloud.exception.UnauthorizedException;
import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.repository.UsuarioRepository;
import com.hackaton.cloud.security.JWTService;
import com.hackaton.cloud.shared.TipoUsuario;
import com.hackaton.cloud.shared.UsuarioDtoCadastro;
import com.hackaton.cloud.shared.login.LoginResponse;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository _usuarioRepository;

    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
	private JWTService jwtService;


    private static final String headerPrefix = "Bearer ";
    
    @Override
    public Page<Usuario> obterTodos(Pageable pageable) {
        return this._usuarioRepository.findAll(pageable);
    }

    @Override
    public List<Usuario> obterTodosAlunos() {
        return this._usuarioRepository.findByTipoUsuario(TipoUsuario.ALUNO);
    }

    @Override
    public List<Usuario> obterTodosProfessores() {
        return this._usuarioRepository.findByTipoUsuario(TipoUsuario.PROFESSOR);
    }

    @Override
	public Optional<Usuario> obterPorId(Long id) {
		Optional<Usuario> encontrado = this._usuarioRepository.findById(id);

		if (!encontrado.isPresent()) {
			throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
		}
		return encontrado;
	}

    public void emailDeUsuarioJaExiste(String email) {
        Optional<Usuario> usuarioProcurado = this._usuarioRepository.findByEmail(email);

        if (usuarioProcurado.isPresent()) {
            throw new BadRequestException("Usuário com o email: " + email + " já encontrado :(");
        }
    }

    @Override
    public Usuario adicionarUsuario(UsuarioDtoCadastro usuarioDto) {
        ModelMapper mapper = new ModelMapper();

        Usuario novoUsuario = mapper.map(usuarioDto, Usuario.class);
        emailDeUsuarioJaExiste(novoUsuario.getEmail());

        String novaSenha = passwordEncoder.encode(novoUsuario.getSenha());
		novoUsuario.setSenha(novaSenha);

        if (novoUsuario.getStatus() == null) {
            novoUsuario.setStatus("");
        }
        novoUsuario.setMatricula(Long.parseLong(gerarMatricula()));
    
        return this._usuarioRepository.save(novoUsuario);    
    }

    public String gerarMatricula() {
        String matricula = "";

       LocalDateTime data = LocalDateTime.now();

       matricula = matricula
            + data.getYear()
            + data.getMonthValue()
            + data.getDayOfYear()
            + data.getHour()
            + data.getMinute()
            + data.getSecond();


        return matricula;
    }

    @Override
    public Usuario atualizarUsuario(Long id, UsuarioDtoCadastro usuario) {
        Optional<Usuario> usuarioAntigo = obterPorId(id);
        ModelMapper mapper = new ModelMapper();

        Usuario usuarioAtualizado = mapper.map(usuario, Usuario.class);
        usuarioAtualizado.setId(id);

         Optional<Usuario> usuarioProcurado = this._usuarioRepository.findByEmail(usuarioAtualizado.getEmail());

        if (usuarioProcurado.isPresent() && !usuarioAntigo.get().getEmail().equals(usuarioAtualizado.getEmail())) {
            throw new BadRequestException("Usuário com o email: " + usuarioAtualizado.getEmail() + " já encontrado :(");
        }
        usuarioAtualizado.setMatricula(usuarioAntigo.get().getMatricula());

        if (usuarioAtualizado.getStatus() == null) {
            usuarioAtualizado.setStatus("");
        }

        return this._usuarioRepository.save(usuarioAtualizado);
    }

    @Override
	public void deletarUsuario(Long id) {
		Optional<Usuario> usuario = obterPorId(id);

		if (!usuario.isPresent()) {
			throw new NotFoundException("Não existe equipe com o id informado: " + id);
		}

		this._usuarioRepository.deleteById(id);
	}

    @Override
	public LoginResponse logar(String login, String senha) {

		try {
			Authentication autenticacao = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login, senha, Collections.emptyList()));

			SecurityContextHolder.getContext().setAuthentication(autenticacao);
			String token = headerPrefix + jwtService.gerarToken(autenticacao);

			Optional<Usuario> usuario = _usuarioRepository.findByEmail(login);
			return new LoginResponse(usuario.get(), token);

		} catch (Exception e) {
			throw new UnauthorizedException("Credenciais inválidas :(");
		}
	}


}
