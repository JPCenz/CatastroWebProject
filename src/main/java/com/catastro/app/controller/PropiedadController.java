package com.catastro.app.controller;

import java.util.List;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.catastro.app.model.DatosGeoespaciales;
import com.catastro.app.model.Propiedad;
import com.catastro.app.repository.DatosGeoespacialesRepository;
import com.catastro.app.service.DatosGeoespacialesService;
import com.catastro.app.service.PropiedadService;
import com.catastro.app.utility.WktUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;



@Controller
@RequestMapping("/property")
public class PropiedadController {
	@Autowired
	DatosGeoespacialesRepository datosRepository;

	@Autowired
	PropiedadService propiedadService;
	
	@Autowired
	DatosGeoespacialesService datosGeoespacialesService;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@GetMapping
	public String getAll(Model model) throws ParseException {
		List<Propiedad> propiedades = propiedadService.listarTodos();
		model.addAttribute("propiedades",propiedades);
//		var d = new DatosGeoespaciales();
//		var p = WktUtil.crearPoligonoDesdeWKT("MULTIPOLYGON(((30 20, 45 40, 10 40, 30 20)),((15 5, 40 10, 10 20, 5 10, 15 5)))");
//		d.setPoligono(p);

		return "propiedades";
	}
	
	@GetMapping("/get/{id}")
	public String getById(@PathVariable("id") Integer id, Model model) throws Exception {
		var propiedadfound = propiedadService.buscarPorId(id);
		if(!propiedadfound.isPresent()) {
			throw new Error();
		}
		Propiedad propiedad = propiedadfound.get();
		var geos = datosRepository.findByPropiedadId(propiedad.getId());
		var geo = geos.stream().findFirst();
		var geoJson = geo.isPresent() && geo.get().getPoligono() != null ?  WktUtil.convertirMultiPolygonAGeoJson(geo.get().getPoligono()) : "";
		model.addAttribute("geoJsonData", geoJson);
		
		


		return "index2";
	}
}
