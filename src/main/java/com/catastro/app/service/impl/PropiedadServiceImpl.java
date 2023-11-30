package com.catastro.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catastro.app.model.Propiedad;
import com.catastro.app.repository.PropiedadRepository;
import com.catastro.app.service.PropiedadService;

import jakarta.transaction.Transactional;

@Service
public class PropiedadServiceImpl implements PropiedadService {
		
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

}
