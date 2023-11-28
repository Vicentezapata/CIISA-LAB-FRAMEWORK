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
import org.springframework.web.bind.annotation.RequestParam;
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
public class CoctelesIngredientesController {

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
	
	@Autowired
	private IngredienteService ingredienteService;
	
	
	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@RequestMapping("formCoctelesIngredientes/{id}")
	public String crearCoctelesIngredientes(@PathVariable(value = "id") Long id,Model model) {
		model.addAttribute("titulo", "Cocteles");
		model.addAttribute("subtitulo", "Agregar ingredientes a coctel");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		List<Medida> medidas = medidaService.findAll();
		List<Ingrediente> ingredientes = ingredienteService.findAll();
		List<CoctelesIngredientes> listcoctelesIngredientes = coctelesIngredientesService.findByCoctelId(id);
		Coctel coctel = coctelService.findOne(id);
		System.out.println(coctel.toString());
		
		CoctelesIngredientes coctelesIngredientes = new CoctelesIngredientes();
		model.addAttribute("coctelesIngredientes", coctelesIngredientes);
		model.addAttribute("listcoctelesIngredientes", listcoctelesIngredientes);
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("coctel", coctel);
		model.addAttribute("medidas", medidas);
		
		return "forms/formCoctelesIngredientes";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	

	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	@PostMapping("/guardarCoctelesIngredientes")
	public String guardarCoctelesIngredientes(
			@RequestParam("medida_id") int medida_id,
            @RequestParam("ingrediente_id") int ingrediente_id,
            @RequestParam("coctel_id") int coctel_id,
            @RequestParam("cantidad") int cantidad,Model model,
			RedirectAttributes flash, SessionStatus status) {
		
		CoctelesIngredientes coctelIngredientes = new CoctelesIngredientes();
		coctelIngredientes.setCantidad(cantidad);
		coctelIngredientes.setCoctel(coctelService.findOne((long) coctel_id));
		coctelIngredientes.setIngrediente(ingredienteService.findOne((long) ingrediente_id));
		coctelIngredientes.setMedida(medidaService.findOne((long) medida_id));
		
		System.out.println(coctelIngredientes);
		
		String mensajeFlash = (coctelIngredientes.getId() != null) ? "Ingrediente editado con éxito!" : "Ingrediente agregado con éxito!";
		coctelesIngredientesService.save(coctelIngredientes);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:formCoctelesIngredientes/"+coctel_id;
		
	}
	

	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	@RequestMapping(value = "/ingredientescoctel/eliminar/{idCoctel}/{id}")
	public String eliminarCoctelIngrediente(
			@PathVariable(value = "idCoctel") Long idCoctel,
			@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS COCTELES_INGREDIENTES VINCULADOS A ESTE COCTEL
			coctelesIngredientesService.delete(id);
			flash.addFlashAttribute("success", "Ingrediente eliminado con éxito!");

		}
		return "redirect:/forms/formCoctelesIngredientes/"+idCoctel;
	}
	
	/********************************************
	 *					VER/EDITAR				*
	 * ******************************************/
	

	
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
