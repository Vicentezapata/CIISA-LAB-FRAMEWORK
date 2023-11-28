package com.example.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.entity.Coctel;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.DetallesPedido;
import com.example.demo.entity.Estado;
import com.example.demo.entity.Ingrediente;
import com.example.demo.entity.Medida;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Usuario;
import com.example.demo.service.CoctelService;
import com.example.demo.service.DetallesPedidoService;
import com.example.demo.service.EstadoService;
import com.example.demo.service.IngredienteService;
import com.example.demo.service.MedidaService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.UsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/forms")
public class PedidoController {

	// INYECTAR VALORES
	@Value("${texto.indexController.index.titulo}")
	private String nombreApp;

	/****************************************************************
	 * INYECCION DE DEPENDECIAS *
	 **************************************************************/

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private DetallesPedidoService detallesPedidoService;

	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

	/********************************************
	 * FORMULARIOS *
	 ******************************************/

	@GetMapping("formPedidos")
	public String crearPedido(Model model) {
		model.addAttribute("opcion", "agregar");
		model.addAttribute("titulo", "Pedidos");
		model.addAttribute("subtitulo", "Agregar pedido");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("pedido", sesion());

		List<Usuario> usuarios = usuarioService.findAll();
		List<Estado> estados = estadoService.findAll();

		Pedido pedido = new Pedido();
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("estados", estados);
		model.addAttribute("pedido", pedido);

		return "forms/formPedidos";
	}

	/********************************************
	 * LISTAR *
	 ******************************************/

	@GetMapping("listarPedidos")
	public String listarPedidos(Model model) {
		List<Pedido> pedidos = pedidoService.findAll();
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("titulo", "Pedidos");
		model.addAttribute("subtitulo", "Listar pedidos");
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("pedido", sesion());
		return "forms/listarPedidos";
	}

	/********************************************
	 * CREAR *
	 ******************************************/

	@PostMapping("/guardarPedido")
	public String guardarPedido(@RequestParam("usuario_id") Long usuario_id, @RequestParam("estado_id") Long estado_id,
			@RequestParam("fechaPedido") String fechaPedido,
			@RequestParam("id") Long id, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (fechaPedido.equals("")) {
			String mensajeFlash = "Campo Fecha despacho es un campo obligatorio!";
			status.setComplete();
			flash.addFlashAttribute("error", mensajeFlash);
			return "redirect:formPedidos";
		} else {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");
			Date fecha = new Date();
			try {
				fecha = formatoFecha.parse(fechaPedido);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Pedido pedido = new Pedido();
			if(id!=null) {
				pedido = pedidoService.findOne(id);
			}
			pedido.setUsuario(usuarioService.findOne(usuario_id));
			pedido.setEstado(estadoService.findOne(estado_id));
			pedido.setFechaPedido(fecha);

			System.out.println(pedido);
			pedidoService.save(pedido);
			// INSERTAMOS
			String mensajeFlash = (pedido.getId() != null) ? "Pedido creado con éxito!" : "Pedido editado con éxito!";
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:/forms/detallepedido/editar/"+pedido.getId();
		}

	}

	/********************************************
	 * ELIMINAR *
	 ******************************************/

	@RequestMapping(value = "pedidos/eliminar/{id}")
	public String eliminarUsusario(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			//OBTENEMOS LOS DETALLES DE PEDIDOS VINCULADOS A ESTE PEDIDO
			List<DetallesPedido> detallesPedidos = detallesPedidoService.findByPedidoId(id);
			for (DetallesPedido detPedido : detallesPedidos) {
				detallesPedidoService.delete(detPedido.getId());	
			}
			pedidoService.delete(id);
			flash.addFlashAttribute("success", "Ususario eliminado con éxito!");
		}
		return "redirect:/forms/listarPedidos";
	}

	/********************************************
	 * VER/EDITAR *
	 ******************************************/

	@RequestMapping(value = "pedidos/{opcion}/{id}")
	public String detallePedido(@PathVariable(value = "opcion") String opcion, @PathVariable(value = "id") Long id,
			Model model, RedirectAttributes flash) {
		Pedido pedido = null;

		if (id > 0) {
			pedido = pedidoService.findOne(id);
			if (pedido == null) {
				flash.addFlashAttribute("error", "El ID del pedido no existe en la BBDD!");
				return "redirect:/forms/listarPedidos";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del pedido no puede ser cero!");
			return "redirect:/forms/listarPedidos";
		}
		List<Usuario> usuarios = usuarioService.findAll();
		List<Estado> estados = estadoService.findAll();

		if (opcion.equals("editar")) {
			model.addAttribute("subtitulo", "Editar Pedido");
		} else {
			model.addAttribute("subtitulo", "Ver Pedido");
		}
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("estados", estados);
		model.addAttribute("opcion", opcion);
		model.addAttribute("pedido", pedido);
		model.addAttribute("nombreApp", nombreApp);
		model.addAttribute("titulo", "Pedidos");
		return "forms/formPedidos";
	}

	/************************************************
	 * MODEL ATRIBUTES *
	 **********************************************/
	@ModelAttribute("usuario")
	public Usuario sesion() {
		Usuario usuario = new Usuario();
		usuario.setNombre("Vicente");
		usuario.setApellido("Zapata");
		usuario.setEmail("vzapata@ciisa.cl");
		return usuario;

	}

}
