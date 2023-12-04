package com.catastro.app.service;

import java.util.List;
import java.util.Optional;

import com.catastro.app.model.Usuario;

import jakarta.servlet.http.HttpSession;

public interface UsuarioService {

	List<Usuario> listarTodos();

	Optional<Usuario> buscarPorId(Integer id);

	Usuario grabar(Usuario empleado) throws Exception;

	void eliminar(Integer id);

	List<Usuario> findByNombreLike(String nombre);

	List<Usuario> findByNombreContaining(String nombre);

	Optional<Usuario> findByCorreo(String email);
	
	public Usuario findBySession(HttpSession session);



}
