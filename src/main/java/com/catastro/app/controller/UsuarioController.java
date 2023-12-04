package com.catastro.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsuarioController {
	
	
	BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();

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
		if(u.getRol()== null) { u.setRol("USER");}
		u.setHashContrasena(passEncode.encode(u.getHashContrasena()));
		usuarioService.grabar(u);
		return "redirect:/users";
		
	}
	
	@RequestMapping(path =   "/register", method = RequestMethod.POST)
	public String register(@Validated @ModelAttribute Usuario u, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			System.err.println("Se presentaron errores en el formulario!");
			return "auth/register";
		}
		if(u.getRol()== null) { u.setRol("USER");}
		u.setHashContrasena(passEncode.encode(u.getHashContrasena()));
		usuarioService.grabar(u);
		return "redirect:/";
		
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
	
	@GetMapping("/login")
	public String login(Model model) {
		//Usuario usuario = new Usuario();
	    //model.addAttribute("loginError", true);
		//model.addAttribute("usuario",usuario);
		return "auth/login";
	}
	

	
//	@PostMapping("/access")
//	public String access(Usuario usuario, HttpSession session) {
//		Optional<Usuario> user=usuarioService.findByCorreo(usuario.getCorreo());
//		System.out.println(user);
//		if (user.isPresent()) {
//			var f = passEncode.matches(usuario.getHashContrasena(), user.get().getHashContrasena());
//			if(f) {
//				System.out.println("Match password");
//				System.out.println("User id:"+user.get().getId());
//				session.setAttribute("idusuario", user.get().getId());
//			}
//			
//			
//			if (user.get().getRol().equals("ADMIN")) {
//				return "redirect:/administrador";
//			}else {
//				return "redirect:/";
//			}
//		}else {
//			System.out.println("NO encontro usuario");
//		}
//		
//		return "redirect:/";
//	}
	

}
