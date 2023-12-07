package com.catastro.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.catastro.app.model.CoordenadasDTO;
import com.catastro.app.model.DatosCatastrales;
import com.catastro.app.model.DatosGeoespaciales;
import com.catastro.app.model.Propiedad;
import com.catastro.app.model.PropiedadFormDTO;
import com.catastro.app.model.Usuario;
import com.catastro.app.repository.DatosGeoespacialesRepository;
import com.catastro.app.repository.DatosCatastralesRepository;
import com.catastro.app.service.DatosGeoespacialesService;
import com.catastro.app.service.PropiedadService;
import com.catastro.app.utility.WktUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/property")
public class PropiedadController {
	@Autowired
	DatosGeoespacialesRepository datosRepository;

	@Autowired
	PropiedadService propiedadService;

	@Autowired
	DatosGeoespacialesService datosGeoespacialesService;

	@Autowired
	DatosCatastralesRepository catastroRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping
	public String getAll(Model model, @RequestParam(name = "search", required = false) String search)
			throws ParseException {
		List<Propiedad> propiedades = new ArrayList<Propiedad>();
		if (search != null && convertirStringAInteger(search) != null) {
			var propiedad = propiedadService.buscarPorId(Integer.parseInt(search));
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

	@GetMapping("/get/{id}")
	public String getById(@PathVariable("id") Integer id, Model model) throws Exception {
		var founded = false;
		var propiedadfound = propiedadService.buscarPorId(id);

		if (!propiedadfound.isPresent()) {
			model.addAttribute("founded", founded);
			return "index2";
		}
		founded = true;
		model.addAttribute("founded", founded);

		Propiedad prop = propiedadfound.get();
		var geos = datosRepository.findByPropiedadId(prop.getId());
		var geo = geos.stream().findFirst();
		var geoJson = geo.isPresent() && geo.get().getPoligono() != null
				? WktUtil.convertirMultiPolygonAGeoJson(geo.get().getPoligono())
				: "";
		model.addAttribute("geoJsonData", geoJson);
		var catastro = catastroRepository.findByPropiedadId(prop.getId()).stream().findFirst();
		var c = catastro.isPresent() ? catastro.get() : new DatosCatastrales();
		model.addAttribute("catastro", c);
		model.addAttribute("propiedad", prop);
		var coor = geo.isPresent() && geo.get().getPoligono() != null ? geo.get().getPoligono().getCentroid() : null;
		var coordenadas = new CoordenadasDTO();
		if (coor != null) {
			coordenadas = new CoordenadasDTO(coor.getX(), coor.getY());

			System.out.println(coordenadas);
		}
		model.addAttribute("coordenadas", coordenadas);

		System.out.println(coor);
		System.out.println(c);

		return "property";
	}

	@GetMapping("/new")
	public String newProperty(Model model) {
		model.addAttribute("propiedad", new PropiedadFormDTO());

		return "propiedad-form";
	}

	@PostMapping("/save")
	public String save(@Validated @ModelAttribute(name = "propiedad") PropiedadFormDTO propiedad, BindingResult result,
			Model model) throws Exception {
		System.out.println(propiedad);
		if (result.hasErrors()) {
			System.err.println("Se presentaron errores en el formulario!");
			System.err.println(result.getAllErrors());
			return "propiedad-form";
		}
		var map = propiedadService.grabarDTO(propiedad);
		System.out.println(map);
		System.out.println(map.get("geo"));
		DatosGeoespaciales g = (DatosGeoespaciales) map.get("geo");
		try {
			MultiPolygon s = WktUtil.crearPoligonoDesdeWKT(propiedad.getStringWKT());
			g.setPoligono(s);
			datosGeoespacialesService.grabar(g);
			System.out.println(s);
		} catch (Exception e) {
			result.addError(new ObjectError("propiedad", "error al convertir WKT STRING"));
			return "propiedad-form";
		}

		model.addAttribute("propiedad", propiedad);

		return "redirect:/property";
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
