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
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CoctelService;
import com.example.demo.service.CoctelesIngredientesService;
import com.example.demo.service.DetallesPedidoService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.MedidaService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class DetallePedidoController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;
	

	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private DetallesPedidoService detallesPedidoService;
	
	@Autowired
	private CoctelService coctelService;
	
	
	
	
	/********************************************
	 *				FORMULARIOS					*
	 * ******************************************/
	
	@RequestMapping("detallepedido/{opcion}/{id}")
	public String crearCoctelesIngredientes(
			@PathVariable(value = "id") Long id,
			@PathVariable(value = "opcion") String opcion,Model model) {
		model.addAttribute("titulo", "Pedidos");
		model.addAttribute("subtitulo", "Agregar detalle a un pedido");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("usuario", sesion());
		
		List<Coctel> cocteles = coctelService.findAll();
		List<DetallesPedido> listDetallesPedidos = detallesPedidoService.findByPedidoId(id);
		
		Pedido pedido = pedidoService.findOne(id);
		System.out.println(pedido.toString());
		
		int total = 0;
		for (DetallesPedido detallesPedido : listDetallesPedidos) {
			total+= detallesPedido.getCoctel().getPrecio() * detallesPedido.getCantidad();
			System.out.println(total);
		}
		
		DetallesPedido detallesPedido = new DetallesPedido();
		
		model.addAttribute("listDetallesPedidos", listDetallesPedidos);
		model.addAttribute("detallesPedido", detallesPedido);
		model.addAttribute("opcion", opcion);
		model.addAttribute("total", total);
		model.addAttribute("cocteles", cocteles);
		model.addAttribute("pedido", pedido);
		
		return "forms/formDetallePedido";
	}
	
	/********************************************
	 *					LISTAR					*
	 * ******************************************/
	

	
	
	/********************************************
	 *					CREAR					*
	 * ******************************************/
	@PostMapping("/guardarDetallePedido")
	public String guardarDetallePedido(
			@RequestParam("pedido_id") int pedido_id,
            @RequestParam("coctel_id") int coctel_id,
            @RequestParam("cantidad") int cantidad,Model model,
			RedirectAttributes flash, SessionStatus status) {
		
		DetallesPedido detallesPedido = new DetallesPedido();
		detallesPedido.setCantidad(cantidad);
		detallesPedido.setCoctel(coctelService.findOne((long) coctel_id));
		detallesPedido.setPedido(pedidoService.findOne((long) pedido_id));
		
		System.out.println(detallesPedido);
		
		String mensajeFlash = (detallesPedido.getId() != null) ? "Coctel editado con éxito!" : "Coctel agregado con éxito!";
		detallesPedidoService.save(detallesPedido);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/forms/detallepedido/editar/"+pedido_id;
		
	}
	

	/********************************************
	 *					ELIMINAR				*
	 * ******************************************/
	@RequestMapping(value = "/detallepedido/eliminar/{idCoctel}/{id}")
	public String eliminarDetallePedido(
			@PathVariable(value = "idCoctel") Long idCoctel,
			@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS COCTELES_INGREDIENTES VINCULADOS A ESTE COCTEL
			detallesPedidoService.delete(id);
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
