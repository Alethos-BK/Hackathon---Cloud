package com.hackaton.cloud.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTAutheticationFilter jwtAuthenticationFilter;


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
    @Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs",
                                    "/configuration/ui",
                                    "/swagger-resources/**",
                                    "/configuration/security",
                                    "/swagger-ui.html",
                                    "/webjars/**");
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors().and().csrf().disable()
			.exceptionHandling()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
		
			.antMatchers(HttpMethod.POST, "/api/login")
			.permitAll()

            .antMatchers(HttpMethod.POST, "/api/login/esqueci-senha")
			.permitAll()
            
            .antMatchers(HttpMethod.GET,"/api/usuarios/avatar/")
			.permitAll()

            .antMatchers(HttpMethod.GET, "/api/equipes/avatar/*")
			.permitAll()

            .antMatchers(HttpMethod.GET, "/api/cargos/avatar/*")
			.permitAll()
            
            .antMatchers(HttpMethod.POST, "/api/usuarios")
			.permitAll()

			//.anyRequest().authenticated();  //aqui vc não pode mais fz tudo
            .anyRequest()
            .permitAll();	
			
			http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
    
}
