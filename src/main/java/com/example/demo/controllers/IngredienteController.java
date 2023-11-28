package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.Ingrediente;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CoctelesIngredientesService;
import com.example.demo.service.IngredienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class IngredienteController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;
	

	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private CoctelesIngredientesService coctelesIngredientesService;
	
	

	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/

	@GetMapping("formIngrediente")
	public String formIngrediente(Model model) {
		model.addAttribute("titulo", "Ingredientes");
		model.addAttribute("opcion", "agregar");
		model.addAttribute("subtitulo", "Agregar Ingrediente");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		
		return "forms/formIngrediente";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarIngredientes")
	public String listarIngrediente(Model model) {
		List<Ingrediente> ingredientes = ingredienteService.findAll();
		model.addAttribute("ingredientes", ingredientes);
		
		model.addAttribute("titulo", "Ingredientes");
		model.addAttribute("subtitulo", "Listar Ingredientes");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		return "forms/listarIngredientes";
	}
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	@PostMapping("/guardarIngrediente")
	public String guardarIngrediente(@Valid Ingrediente ingrediente, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Ingredientes");
			model.addAttribute("subtitulo", "Agregar Ingrediente");
			model.addAttribute("opcion", "agregar");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("usuario", sesion());
			return "forms/formIngrediente";
		}
		String mensajeFlash = (ingrediente.getId() != null) ? "Ingrediente editado con éxito!" : "Ingrediente creado con éxito!";
		ingredienteService.save(ingrediente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarIngredientes";
		
	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	
	@RequestMapping(value = "ingredientes/eliminar/{id}")
	public String eliminarIngrediente(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS COCTELES_INGREDIENTES VINCULADOS A ESTE INGREDIENTE
			List<CoctelesIngredientes> coctelesIngredientes = ingredienteService.findCoctelIngById(id);
			for (CoctelesIngredientes coctIng : coctelesIngredientes) {
				coctelesIngredientesService.delete(coctIng.getId());	
			}
			ingredienteService.delete(id);
			flash.addFlashAttribute("success", "Ingrediente eliminado con éxito!");
		}
		return "redirect:/forms/listarIngredientes";
	}
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "ingredientes/{opcion}/{id}")
	public String detalleIngrediente(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Ingrediente ingrediente = null;
		
		if (id > 0) {
			ingrediente = ingredienteService.findOne(id);
			if (ingrediente == null) {
				flash.addFlashAttribute("error", "El ID del ingrediente no existe en la BBDD!");
				return "redirect:/forms/listarIngredientes";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del ingrediente no puede ser cero!");
			return "redirect:/forms/listarIngredientes";
		}
		System.out.println(ingrediente.toString());
		model.addAttribute("opcion", opcion);
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Ingrediente");
		}
		else {
			model.addAttribute("subtitulo", "Ver Ingrediente");
		}
		model.addAttribute("titulo", "Ingredientes");
		return "forms/formIngrediente";
	}
	
	@GetMapping(value = "/cargar-ingredientes/{term}", produces = { "application/json" })
	public @ResponseBody List<Ingrediente> cargarIngredientes(@PathVariable String term) {
		System.out.print(ingredienteService.findByNombre(term));
		return ingredienteService.findByNombre(term);
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
