package com.example.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//ERROR import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Configuration
public class SpringSecurityConfig {

	@Autowired
    private UsuarioService usuarioService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		List<Usuario> usuarios = usuarioService.findAll();
	    for (Usuario usuario : usuarios) {
	        manager.createUser(
	            User.withUsername(usuario.getEmail())
	            .password(usuario.getPassword())
	            .roles(usuario.getRol())
	            .build()
	        );
	    }
		return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(crsf -> crsf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		.headers(headers -> headers.frameOptions(frame -> frame.disable()))	
		.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/adminlte/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/fragments/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/register")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/forms/registrarUsuario")).permitAll()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/forms/form*")).hasAnyRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/forms/listar*")).hasRole("USER")
			.requestMatchers(new AntPathRequestMatcher("/forms/listar*")).hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/forms/*/editar/*")).hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/forms/*/eliminar/*")).hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll() // Permitir acceso a la consola H2
			.anyRequest().authenticated())
		.formLogin(login -> login.loginPage("/login").permitAll())
		.logout(logout -> logout.permitAll())
		.exceptionHandling(exception -> exception.accessDeniedPage("/error_403"));

		return http.build();
	}

}
