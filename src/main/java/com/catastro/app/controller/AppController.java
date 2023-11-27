package com.catastro.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {

	@GetMapping({ "", "/", "/home", "/index" })
	public String home(Model model ,HttpSession session) {
		var s = session.getAttribute("idusuario");
		System.out.println("session: "+s);
		model.addAttribute("titulo", "SALUDO");
		model.addAttribute("mensaje", "Bienvenido a Thymeleaf.");
		return "index";
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
	
	@GetMapping({ "/auditorias" })
	public String auditoria(Model model) {
		model.addAttribute("titulo", "SALUDO");
		model.addAttribute("mensaje", "Bienvenido a Thymeleaf.");
		return "auditoria";
	}

}
