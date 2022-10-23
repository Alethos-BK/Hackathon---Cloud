package com.hackaton.cloud.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.hackaton.cloud.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService  {
    private static final String chavePrivadaJWT = "cloud"; 
	
	public String gerarToken(Authentication authentication) {
				
		int tempoExpiracao = 86400000; //15 minutos 900000  1 dia 86400000
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		Usuario user =  (Usuario) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512, chavePrivadaJWT)
				.compact();
	}
	
	public Optional<Long> obterIdDoUsuario(String token){
		try {
			Claims claims = parse(token).getBody();
			return Optional.ofNullable(Long.parseLong(claims.getSubject()));
			
		} catch (Exception e) {
			return Optional.empty();
		}
	}
	
	private Jws<Claims> parse(String token){
		return Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token);
	}
}
