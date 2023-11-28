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
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.DetallesPedido;
import com.example.demo.entity.Ingrediente;
import com.example.demo.entity.Medida;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CoctelService;
import com.example.demo.service.CoctelesIngredientesService;
import com.example.demo.service.DetallesPedidoService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.MedidaService;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class CoctelController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;
	

	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	
	@Autowired
	private CoctelService coctelService;
	
	@Autowired
	private CoctelesIngredientesService coctelesIngredientesService;
	
	@Autowired
	private MedidaService medidaService;
	
	
	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@GetMapping("formCoctel")
	public String crearCoctel(Model model) {
		model.addAttribute("titulo", "Cocteles");
		model.addAttribute("opcion", "agregar");
		model.addAttribute("subtitulo", "Agregar coctel");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		List<Medida> medidas = medidaService.findAll();
		//System.out.println(medidas.size());
		Coctel coctel = new Coctel();
		model.addAttribute("coctel", coctel);
		model.addAttribute("medidas", medidas);
		
		return "forms/formCoctel";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	
	@GetMapping("listarCocteles")
	public String listarCocteles(Model model) {
		List<Coctel> cocteles = coctelService.findAll();
		model.addAttribute("cocteles", cocteles);
		
		model.addAttribute("titulo", "Cocteles");
		model.addAttribute("subtitulo", "Listar cocteles");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		return "forms/listarCocteles";
	}
	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	
	@PostMapping("/guardarCoctel")
	public String guardarCoctel(@Valid Coctel coctel, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		//INSERTAMOS
		if (result.hasErrors()) {
			model.addAttribute("opcion", "agregar");
			model.addAttribute("titulo", "Cocteles");
			model.addAttribute("subtitulo", "Agregar coctel");
			model.addAttribute("usuario", sesion());
			model.addAttribute("nombreApp", nombreApp);
			return "forms/formCoctel";
		}
		String mensajeFlash = (coctel.getId() != null) ? "Coctel editado con éxito!" : "Coctel creada con éxito!";
		coctelService.save(coctel);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listarCocteles";
		
	}
	
	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	@RequestMapping(value = "cocteles/eliminar/{id}")
	public String eliminarCoctel(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS COCTELES_INGREDIENTES VINCULADOS A ESTE COCTEL
			List<CoctelesIngredientes> coctelesIngredientes = coctelService.findCoctelIngById(id);
			for (CoctelesIngredientes coctIng : coctelesIngredientes) {
				coctelesIngredientesService.delete(coctIng.getId());	
			}
			//OBTENEMOS LOS DETALLES_PEDIDO VINCULADOS A ESTE COCTEL
			List<DetallesPedido> detallesPedidos = coctelService.findDetallesPedidosById(id);
			if(detallesPedidos.size()>0) {
				flash.addFlashAttribute("error", "Coctel no pudo ser eliminado ya que existen pedidos dependientes de el!");
			}else {
				coctelService.delete(id);
				flash.addFlashAttribute("success", "Coctel eliminado con éxito!");
			}
		}
		return "redirect:/forms/listarCocteles";
	}
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	
	@RequestMapping(value = "cocteles/{opcion}/{id}")
	public String detalleCoctel(
			@PathVariable(value = "opcion") String opcion,
			@PathVariable(value = "id") Long id,Model model, RedirectAttributes flash) {
		Coctel coctel = null;
		
		if (id > 0) {
			coctel = coctelService.findOne(id);
			if (coctel == null) {
				flash.addFlashAttribute("error", "El ID del coctel no existe en la BBDD!");
				return "redirect:/forms/listarCoctels";
			}
		}
		else {
			flash.addFlashAttribute("error", "El ID del coctel no puede ser cero!");
			return "redirect:/forms/listarCoctels";
		}
		model.addAttribute("opcion", opcion);
		model.addAttribute("coctel", coctel);
		model.addAttribute("nombreApp", nombreApp);
		if(opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Coctel");
		}
		else {
			model.addAttribute("subtitulo", "Ver Coctel");
		}
		List<CoctelesIngredientes> coctelesIngredientes = coctelService.findCoctelIngById(id);
		model.addAttribute("coctelesIngredientes", coctelesIngredientes);
		model.addAttribute("titulo", "Coctels");
		return "forms/formCoctel";
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
