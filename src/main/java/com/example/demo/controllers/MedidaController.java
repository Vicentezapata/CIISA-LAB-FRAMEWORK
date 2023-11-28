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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Medida;
import com.example.demo.entity.Usuario;
import com.example.demo.service.MedidaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class MedidaController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;
	

	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	
	@Autowired
	private MedidaService medidaService;
	
	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formMedida")
	public String crearMedida(Model model) {
		model.addAttribute("titulo", "Medidas");
		model.addAttribute("subtitulo", "Agregar medida");
		model.addAttribute("opcion", "agregar");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		Medida medida = new Medida();
		model.addAttribute("medida", medida);
		
		return "forms/formMedida";
	}
	
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarMedidas")
	public String listarMedidas(Model model) {
		List<Medida> medidas = medidaService.findAll();
		model.addAttribute("medidas", medidas);
		model.addAttribute("titulo", "Medidas");
		model.addAttribute("subtitulo", "Listar medidas");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		return "forms/listarMedidas";
	}
	
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarMedida")
	public String guardarMedida(@Valid Medida medida, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Medidas");
			model.addAttribute("subtitulo", "Agregar Medida");
			model.addAttribute("nombreApp", nombreApp);
			model.addAttribute("usuario", sesion());
			return "forms/formMedida";
		}
		String mensajeFlash = (medida.getId() != null) ? "Medida editado con éxito!" : "Medida creada con éxito!";
		medidaService.save(medida);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarMedidas";
		
	}
	
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	
	@RequestMapping(value = "medidas/eliminar/{id}")
	public String eliminarMedida(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			medidaService.delete(id);
			flash.addFlashAttribute("success", "Medida eliminado con éxito!");
		}
		return "redirect:/forms/listarMedidas";
	}
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "medidas/{opcion}/{id}")
	public String detalleIngrediente(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Medida medida = null;
		
		if (id > 0) {
			medida = medidaService.findOne(id);
			if (medida == null) {
				flash.addFlashAttribute("error", "El ID del ingrediente no existe en la BBDD!");
				return "redirect:/forms/listarIngredientes";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del ingrediente no puede ser cero!");
			return "redirect:/forms/listarIngredientes";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("medida", medida);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Medida");
		}
		else {
			model.addAttribute("subtitulo", "Ver Medida");
		}
		model.addAttribute("titulo", "Medidas");
		return "forms/formMedida";
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
