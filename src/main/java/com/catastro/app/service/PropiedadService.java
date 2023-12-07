package com.catastro.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.catastro.app.model.Propiedad;
import com.catastro.app.model.PropiedadFormDTO;

public interface PropiedadService {
	
	List<Propiedad> listarTodos();

	Optional<Propiedad> buscarPorId(Integer id);

	Propiedad grabar(Propiedad propiedad) throws Exception;

	void eliminar(Integer id);
	
	HashMap<String, Object> grabarDTO(PropiedadFormDTO p) throws Exception;
	
	List<Propiedad> findByIdContaining(String id);

}
