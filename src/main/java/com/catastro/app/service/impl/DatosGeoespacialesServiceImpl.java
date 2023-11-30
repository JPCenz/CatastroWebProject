package com.catastro.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catastro.app.model.DatosGeoespaciales;
import com.catastro.app.repository.DatosGeoespacialesRepository;
import com.catastro.app.service.DatosGeoespacialesService;
@Service
public class DatosGeoespacialesServiceImpl implements DatosGeoespacialesService {
	
	@Autowired
	DatosGeoespacialesRepository repository;
	
	@Override
	public List<DatosGeoespaciales> listarTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<DatosGeoespaciales> buscarPorId(Integer id) {
		return repository.findById(id);
	}

	@Override
	public DatosGeoespaciales grabar(DatosGeoespaciales datos) throws Exception {
//		if (datos.getPoligono()!=null){
//			
//		}
		return repository.save(datos);
	}

	@Override
	public void eliminar(Integer id) {
		repository.deleteById(id);
		
	}

}
