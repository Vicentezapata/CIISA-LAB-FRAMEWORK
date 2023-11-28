package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.User;
import com.example.demo.models.Producto;


@Controller
public class IndexController {
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
		@Value("${texto.indexController.index.titulo}")
		private String nombreApp;
	
	@GetMapping({"/login"})
	public String login(Model model) {
		model.addAttribute("titulo", "Iniciar sesión");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("subtitulo", "Home");
		//retorna el nombre del template
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("titulo", "Formulario de registro");
		model.addAttribute("nombreApp", nombreApp);
		return "register";
	}
	
	@GetMapping({"/home","/","/index"})
	public String home(Model model) {
		model.addAttribute("nombreApp",nombreApp);
		User user = new User();
		user.setNombre("Ignacio");
		user.setaPaterno("Zúniga");
		user.setaMaterno("Arancibia");
		user.setEmail("izuniga@vapp.cl");
		user.setRut("20.537.667-4");
		user.setRol("Administrador");
		model.addAttribute("user",user);
		model.addAttribute("titulo", "Página Principal");
		model.addAttribute("subtitulo", "Home");
		return "home";
	}
}

/*@GetMapping({"/index","/","/index"})
public String index(Model model) {
	//INSTANCIAMOS EL USER
	User user = new User();
	user.setNombre("Ignacio");
	user.setaPaterno("Zuñiga");
	user.setaMaterno("Arancibia");
	user.setEmail("izuniga@edig.cl");
	user.setRut("1-9");
	user.setRol("ADMIN");
	//ENTREGANDO VALORES A LA VISTA
	model.addAttribute("user",user);
	//model.addAttribute("productos",poblarProductos());
	model.addAttribute("nombreApp",nombreApp);
	model.addAttribute("titulo", "Dashboard");
	model.addAttribute("subtitulo", "Dashboard principal");
	return "index";*/


/*@ModelAttribute("productos")
public List<Producto> poblarProductos(){
	List<Producto> productos = new ArrayList<>();
	productos.add(new Producto("Shoes Bapesta Negras con blanco",1,"Zapatillas Bape",1,1,1,1,10,100000));
	productos.add(new Producto("Shoes Bapesta Moradas con blanco",2,"Zapatillas Bape",1,1,1,1,8,100000));
	productos.add(new Producto("Polera Supreme",3,"Prendas superiores",1,1,1,1,8,100000));
	productos.add(new Producto("Poleron Louis Voutton",4,"Polerones",1,1,1,1,8,100000));
	productos.add(new Producto("Pasamontañas nike",5,"StreetWear",1,1,1,1,8,100000));
	productos.add(new Producto("Pasamontañas nike",6,"StreetWear",1,1,1,1,8,100000));
	productos.add(new Producto("Pasamontañas nike",7,"StreetWear",1,1,1,1,8,100000));
	productos.add(new Producto("Pasamontañas nike",8,"StreetWear",1,1,1,1,8,100000));
	return productos;
}*/
	
	/*
	 @GetMapping("/index2")
	//OPERACION ModelAndView
	public ModelAndView index2(ModelAndView mv) {
		//OPCION 2
		mv.addObject("user","Ignacio Zuñiga");
		mv.setViewName("index2");
		return mv;
	}*/
	

