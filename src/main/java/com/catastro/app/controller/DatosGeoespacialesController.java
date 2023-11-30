package com.catastro.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.catastro.app.service.DatosGeoespacialesService;

@Controller
@RequestMapping("/datosgeoespaciales")
public class DatosGeoespacialesController {
	
	@Autowired
	DatosGeoespacialesService service;
	
	@GetMapping
	public String getAll() {
		return "index";
	}
}
