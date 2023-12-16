package com.catastro.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.catastro.app.model.Propiedad;
import com.catastro.app.repository.DatosCatastralesRepository;
import com.catastro.app.repository.DatosGeoespacialesRepository;
import com.catastro.app.service.DatosGeoespacialesService;
import com.catastro.app.service.PropiedadService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	DatosGeoespacialesRepository datosRepository;

	@Autowired
	PropiedadService propiedadService;

	@Autowired
	DatosGeoespacialesService datosGeoespacialesService;

	@Autowired
	DatosCatastralesRepository catastroRepository;

	@GetMapping("/get")
	public String getAll(Model model, @RequestParam(name = "numpredio", required = false) String numpredio)
			throws ParseException {
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		if (numpredio != null && convertirStringAInteger(numpredio) != null) {
			var propiedad = propiedadService.buscarPorId(Integer.parseInt(numpredio));
			if(propiedad.isPresent())  propiedades.add(propiedad.get());
			
			model.addAttribute("propiedades", propiedades);
			System.out.println(propiedades);
			return "propiedades";
		}

		propiedades = propiedadService.listarTodos();
		model.addAttribute("propiedades", propiedades);
//		var d = new DatosGeoespaciales();
//		var p = WktUtil.crearPoligonoDesdeWKT("MULTIPOLYGON(((30 20, 45 40, 10 40, 30 20)),((15 5, 40 10, 10 20, 5 10, 15 5)))");
//		d.setPoligono(p);

		return "propiedades";
	}
	
	@GetMapping()
	public String searchByParams(Model model, @RequestParam(name = "numpredio", required = false) String numpredio) {
		
		
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		if (numpredio != null && convertirStringAInteger(numpredio) != null) {
			var propiedad = propiedadService.buscarPorId(Integer.parseInt(numpredio));
			if(propiedad.isPresent())  propiedades.add(propiedad.get());
			
			model.addAttribute("propiedades", propiedades);
			model.addAttribute("search", true);
			System.out.println(propiedades);
			return "search";
		}

		//propiedades = propiedadService.listarTodos();
		model.addAttribute("search", false);
		
		return "search";
	}
	
	private Integer convertirStringAInteger(String str) {
	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	        // Manejar la excepción, por ejemplo, devolver null o un valor predeterminado
	        return null; // o cualquier otro valor predeterminado
	    }
	}


}
