package com.catastro.app.service;

import java.util.List;
import java.util.Optional;

import com.catastro.app.model.DatosGeoespaciales;

public interface DatosGeoespacialesService {

	List<DatosGeoespaciales> listarTodos();

	Optional<DatosGeoespaciales> buscarPorId(Integer id);

	DatosGeoespaciales grabar(DatosGeoespaciales datos) throws Exception;

	void eliminar(Integer id);

}
