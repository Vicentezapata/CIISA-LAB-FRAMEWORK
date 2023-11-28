package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Coctel;
import com.example.demo.entity.DetallesPedido;
import com.example.demo.entity.Ingrediente;
import com.example.demo.entity.Medida;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CoctelService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.MedidaService;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class UsuarioController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	
	@Autowired
	private UsuarioService usuarioService;
	
	

	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formUsuario")
	public String crearUsuario(Model model) {
		model.addAttribute("opcion", "agregar");
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Agregar usuario");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		
		return "forms/formUsuario";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarUsuarios")
	public String listarUsuarios(Model model) {
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("titulo", "Usuarios");
		model.addAttribute("subtitulo", "Listar usuarios");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		return "forms/listarUsuarios";
	}
	
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Usuarios");
			model.addAttribute("subtitulo", "Agregar usuario");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("usuario", sesion());
			return "forms/formUsuario";
		}
		String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con éxito!" : "Usuario creada con éxito!";
		usuarioService.save(usuario);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarUsuarios";
		
	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	
	@RequestMapping(value = "usuarios/eliminar/{id}")
	public String eliminarUsusario(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS DETALLES_PEDIDO VINCULADOS A ESTE COCTEL
			List<Pedido> pedidos = usuarioService.findPedidosById(id);
			if(pedidos.size()>0) {
				flash.addFlashAttribute("error", "Usuario no pudo ser eliminado ya que existen pedidos dependientes de el!");
			}else {
				usuarioService.delete(id);
				flash.addFlashAttribute("success", "Coctel eliminado con éxito!");
			}
		}
		return "redirect:/forms/listarUsuarios";
	}
	
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "usuarios/{opcion}/{id}")
	public String detalleUsuario(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Usuario usuario = null;
		
		if (id > 0) {
			usuario = usuarioService.findOne(id);
			if (usuario == null) {
				flash.addFlashAttribute("error", "El ID del usuario no existe en la BBDD!");
				return "redirect:/forms/listarUsuarios";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del usuario no puede ser cero!");
			return "redirect:/forms/listarUsuarios";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("usuario", usuario);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Usuario");
		}
		else {
			model.addAttribute("subtitulo", "Ver Usuario");
		}
		model.addAttribute("titulo", "Usuarios");
		return "forms/formUsuario";
	}
	
	/************************************************
	 *				MODEL ATRIBUTES					*
	 * **********************************************/
	@ModelAttribute("usuario")
	public Usuario sesion() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Vicente");
		usuario.setApellido("Zapata");
		usuario.setEmail("vzapata@ciisa.cl");
		return usuario;

	}

}
