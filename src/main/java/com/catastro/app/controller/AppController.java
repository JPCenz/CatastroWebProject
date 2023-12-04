package com.catastro.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;

import com.catastro.app.model.Usuario;
import com.catastro.app.service.UsuarioService;
import com.catastro.app.utility.UserUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 
 */

@Controller
public class AppController {
	@Autowired
	UsuarioService userService;

	@GetMapping({ "", "/" })
	public String home(Model model ,HttpSession session) {
		var s = session.getAttribute("idusuario");
		System.out.println("session: "+s);
		
		var u =userService.findBySession(session);
		model.addAttribute("user", u.getNombres());

		return "index-user";
	}
	
	/**
	 * Redireccion a la pagina de usuarios anonimos
	 * @param model
	 * @return
	 */
	
	@GetMapping({ "/home" })
	public String home(Model model) {
		model.addAttribute("titulo", "SALUDO");
		model.addAttribute("mensaje", "Bienvenido a Thymeleaf.");
		return "index-anonymous";
	}
	
	@GetMapping({ "/propiedades" })
	public String propiedades(Model model) {
		model.addAttribute("titulo", "SALUDO");
		model.addAttribute("mensaje", "Bienvenido a Thymeleaf.");
		return "propiedades";
	}
	
	@GetMapping({ "/propietarios" })
	public String propietarios(Model model) {
		model.addAttribute("titulo", "SALUDO");
		model.addAttribute("mensaje", "Bienvenido a Thymeleaf.");
		return "propietarios";
	}
	


	
	@GetMapping("/register")
	public String register(Model model) {

		 model.addAttribute("usuario", new Usuario());
		return "auth/register";
	}
	
	/** Error page. */
	@RequestMapping("/error.html")
	public String error(HttpServletRequest request, Model model) {
		System.out.println(model);

		model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("<ul>");
		while (throwable != null) {
			errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
			throwable = throwable.getCause();
		}
		errorMessage.append("</ul>");
		model.addAttribute("errorMessage", errorMessage.toString());
		return "error";
	}

}
