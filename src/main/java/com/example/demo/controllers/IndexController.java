package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Coctel;
import com.example.demo.entity.Ingrediente;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CoctelService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.UsuarioService;

@Controller
public class IndexController {
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	
	@Autowired
	private CoctelService coctelService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;
	
	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/

	@GetMapping({"/","/index","/home"})
	public String index(Model model) {
		
		List<Coctel> cocteles = coctelService.findAll();
		model.addAttribute("cocteles", cocteles);
		
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		
		List<Ingrediente> ingredientes = ingredienteService.findAll();
		model.addAttribute("ingredientes", ingredientes);
		
		List<Pedido> pedidos = pedidoService.findAll();
		model.addAttribute("pedidos", pedidos);
		
		Usuario usuario = new Usuario();
		usuario.setNombre("Vicente");
		usuario.setApellido("Zapata");
		usuario.setEmail("vzapata@ciisa.cl");
		model.addAttribute("titulo", "Dashboard");
		model.addAttribute("subtitulo", "Dashboard Principal");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", usuario);
		
		//PROBRAR TEXTO ENCRIPTADO
		String textoPlano = "Ciisa123";
        String textoEncriptado = passwordEncoder.encode(textoPlano);
        System.out.println("Texto encriptado: " + textoEncriptado);
        
        textoPlano = "admin123";
        textoEncriptado = passwordEncoder.encode(textoPlano);
        System.out.println("Texto encriptado: " + textoEncriptado);

		
		return "index";
	}

	/*@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("titulo", "Iniciar sesión");
		model.addAttribute("nombreApp", nombreApp);
		return "login";
	}
	*/
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("titulo", "Formulario de registro");
		model.addAttribute("nombreApp", nombreApp);
		return "register";
	}

}
