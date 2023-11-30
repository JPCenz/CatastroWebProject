package com.catastro.app.service;

import java.util.List;
import java.util.Optional;

import com.catastro.app.model.Propiedad;

public interface PropiedadService {
	
	List<Propiedad> listarTodos();

	Optional<Propiedad> buscarPorId(Integer id);

	Propiedad grabar(Propiedad propiedad) throws Exception;

	void eliminar(Integer id);

}
