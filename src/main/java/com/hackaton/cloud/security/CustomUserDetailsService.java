package com.hackaton.cloud.security;


import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hackaton.cloud.model.Usuario;
import com.hackaton.cloud.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository _repositorioUsuario;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = getUser(() -> _repositorioUsuario.findByEmail(login));
		return (UserDetails) usuario;
	}

	public Usuario loadUserById(Long id) {
		Usuario usuario = getUser(() -> _repositorioUsuario.findById(id));
		return usuario;
	}

	private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
		return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
    
}
