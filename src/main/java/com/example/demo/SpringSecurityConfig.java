package com.example.demo;

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

@Configuration
public class SpringSecurityConfig {
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("Veronika")
				.password(passwordEncoder().encode("user"))
				.roles("USER").build()
		);
		manager.createUser(
				User.withUsername("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN","USER").build()
		);
		return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(new AntPathRequestMatcher("/adminlte/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/fragments/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/register")).permitAll()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/forms/form*")).hasAnyRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/forms/listar*")).hasRole("USER")
			.requestMatchers(new AntPathRequestMatcher("/forms/listar*")).hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/forms/*/editar/*")).hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/forms/*/eliminar/*")).hasRole("ADMIN")
			.anyRequest().authenticated())
		.formLogin(login -> login.loginPage("/login").permitAll())
		.logout(logout -> logout.permitAll())
		.exceptionHandling(exception -> exception.accessDeniedPage("/error_403"));

		return http.build();
	}

}
