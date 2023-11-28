package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.entity.Usuario;

@Controller
public class IndexController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;

	@GetMapping({"/","/index","/home"})
	public String index(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Vicente");
		usuario.setApellido("Zapata");
		usuario.setEmail("vzapata@ciisa.cl");
		model.addAttribute("titulo", "Dashboard");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", usuario);
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("titulo", "Iniciar sesión");
		model.addAttribute("nombreApp", nombreApp);
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("titulo", "Formulario de registro");
		model.addAttribute("nombreApp", nombreApp);
		return "register";
	}

}
