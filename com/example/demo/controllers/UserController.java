package com.example.demo.controllers;

import java.util.ArrayList;
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

import com.example.demo.entity.Estado;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/forms")
public class UserController {
	
	private List<User> userList = new ArrayList<>();
	
	/****************************************************************
	 *					INYECCION DE DEPENDECIAS					*
	 * **************************************************************/
		@Value("${texto.indexController.index.titulo}")
		private String nombreApp;
		
		
		/********************************************
		 *				FORMULARIOS					*
		 * ******************************************/
		@GetMapping("formUsers")
			    public String newUserForm(Model model) {
			model.addAttribute("subtitulo","Agregar Usuario");
			        model.addAttribute("user", new User());
			        return "forms/formUsers";
			    }
		
		@PostMapping("/user")
	    public String saveUser(@ModelAttribute User user) {
	        user.setUserId((long) (userList.size() + 1)); // Asignar un ID único
	        userList.add(user);
	        return "redirect:/user";
	    }
		
		/************************************************
		 *				MODEL ATRIBUTES					*
		 * **********************************************/
		@ModelAttribute("user")
		public User sesion() {
			User user = new User();
			user.setNombre("Sanyer");
			user.setaPaterno("Sanyer");
			user.setaMaterno("Sanyer");
			return user;
		}
		
		@GetMapping("/listarUsers")
		public String listarUsers(Model model) {
			model.addAttribute("user", userList);
			return "forms/listarUsers";
		}
		
		/*
	    
	    @PostMapping("/user")
	    public String saveUser(@ModelAttribute User user) {
	        user.setUserId((long) (userList.size() + 1)); // Asignar un ID único
	        userList.add(user);
	        return "redirect:/user";
	    }
	    
	    @GetMapping("/user/edit/{userId}")
	    public String editUserForm(@PathVariable Long userId, Model model) {
	        User user = findUserByUserId(userId);
	        model.addAttribute("user", user);
	        return "formUser";
	    }
	    
	    @PostMapping("/user/edit/{userId}")
	    public String updateUser(@PathVariable Long userId, @ModelAttribute User updatedUser) {
	        User user = findUserByUserId(userId);
	        if (user != null) {
	            user.setNombre(updatedUser.getNombre());
	            user.setEmail(updatedUser.getEmail());
	        }
	        return "redirect:/user";
	    }
	    
	    @GetMapping("/user/delete/{userId}")
	    public String deleteUser(@PathVariable Long userId) {
	        userList.removeIf(user -> user.getUserId().equals(userId));
	        return "redirect:/user";
	    }
	    
	    private User findUserByUserId(Long userId) {
	        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
	    }
	    
	    @GetMapping("formUsers")
		public String login(Model model) {
			model.addAttribute("user", sesion());
			model.addAttribute("titulo", "Iniciar Sesión");
			model.addAttribute("subtitulo","Agregar Usuario");
			model.addAttribute("nombreApp", nombreApp);
			return "forms/formUsers";
		}
	    */
		
		
		

		
		/********************************************
		 *					LISTAR					*
		 * ******************************************/
		
		
		
		
		
		/********************************************
		 *					CREAR					*
		 * ******************************************/
		
		
		
		/********************************************
		 *					ELIMINAR				*
		 * ******************************************/
		
		
		
		
		/********************************************
		 *					VER/EDITAR				*
		 * ******************************************/
		
}
