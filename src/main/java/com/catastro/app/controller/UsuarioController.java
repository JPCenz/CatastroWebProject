package com.catastro.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.catastro.app.model.Usuario;
import com.catastro.app.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;

	@GetMapping
	public String usuarios(Model model) {
		 
		List<Usuario> usuarios = usuarioService.listarTodos();
		model.addAttribute("usuarios",usuarios);
		return "usuarios";
	}
	
	
	@GetMapping("/new")
	public String nuevoUsuario(Model model) {
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario",usuario);
		return "usuarios-form";
	}
	
	
	
	@RequestMapping(path =   "/save", method = RequestMethod.POST)
	public String guardar(@Validated @ModelAttribute Usuario u, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			System.err.println("Se presentaron errores en el formulario!");
			return "usuarios-form";
		}
		usuarioService.grabar(u);
		return "redirect:/users";
		
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Optional<Usuario> usuario = usuarioService.buscarPorId(id);
		model.addAttribute("titulo", "EDITAR usuario (" + id + ")");
		model.addAttribute("usuario", usuario);
		return "usuarios-form";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer id) throws Exception {
		usuarioService.eliminar(id);
		return "redirect:/users";
		
	}
	

}
