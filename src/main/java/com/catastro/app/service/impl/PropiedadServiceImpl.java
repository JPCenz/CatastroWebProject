package com.catastro.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catastro.app.model.DatosGeoespaciales;
import com.catastro.app.model.Propiedad;
import com.catastro.app.model.PropiedadFormDTO;
import com.catastro.app.repository.DatosCatastralesRepository;
import com.catastro.app.repository.DatosGeoespacialesRepository;
import com.catastro.app.repository.PropiedadRepository;
import com.catastro.app.service.DatosCatastralesService;
import com.catastro.app.service.DatosGeoespacialesService;
import com.catastro.app.service.PropiedadService;

import jakarta.transaction.Transactional;

@Service
public class PropiedadServiceImpl implements PropiedadService {
	
	@Autowired
	DatosCatastralesService catastroService;
	
	@Autowired
	DatosCatastralesRepository catastroRepository;
	
	@Autowired
	DatosGeoespacialesRepository geoRepository;
	
	@Autowired
	DatosGeoespacialesService geoService;
		
	@Autowired
	PropiedadRepository propiedadRepository;
	
	@Override
	public List<Propiedad> listarTodos() {
		return propiedadRepository.findAll();
		
	}

	@Override
	@Transactional
	public Optional<Propiedad> buscarPorId(Integer id) {
		System.out.println(propiedadRepository.findById(id));
		return propiedadRepository.findById(id);
		
	}

	@Override
	public Propiedad grabar(Propiedad propiedad) throws Exception {
		return propiedadRepository.save(propiedad);
		
	}

	@Override
	public void eliminar(Integer id) {
		propiedadRepository.deleteById(id);

	}
	
	public HashMap<String, Object> grabarDTO(PropiedadFormDTO p) {
		var propiedad = new Propiedad();
		
		propiedad.setDescripcion(p.getDescripcion());
		propiedad.setDireccion(p.getDireccion());
		propiedad.setEstado(p.getEstado());
		propiedad.setTipoPropiedad(p.getTipoPropiedad());
		propiedad.setTituloPropiedad(p.getTipoPropiedad());
		
		propiedad = propiedadRepository.save(propiedad);
		
		DatosGeoespaciales geo = new DatosGeoespaciales();
		geo.setPropiedad(propiedad);
		try {
			geoService.grabar(geo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		var catastral = catastroService.fromDTO(p.getDatoCatastral()); 
		catastral.setPropiedad(propiedad);
		catastroRepository.save(catastral);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("propiedad", propiedad);
		map.put("geo", geo);
		map.put("catastro", catastral);
		
		return map;
	}
	
	public List<Propiedad> findByIdContaining(String id){
		return propiedadRepository.findByIdContaining(id);
		
	};

}
