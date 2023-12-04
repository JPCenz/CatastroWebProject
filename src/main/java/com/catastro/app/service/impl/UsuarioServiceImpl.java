package com.catastro.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catastro.app.model.Usuario;
import com.catastro.app.repository.UsuarioRepository;
import com.catastro.app.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario grabar(Usuario empleado) throws Exception {

		return usuarioRepository.save(empleado);
	}

	@Override
	public void eliminar(Integer id) {
		usuarioRepository.deleteById(id);

	}

	@Override
	public List<Usuario> findByNombreLike(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> findByNombreContaining(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuario> findByCorreo(String email) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByCorreo(email);
	}
	
	public Usuario findBySession(HttpSession session) {
		// TODO Auto-generated method stub
		var  id = session.getAttribute("idusuario");
		var actualId = id != null ? Integer.parseInt(id.toString()) : null;
		var user =  actualId != null ? usuarioRepository.findById(actualId) : null;
		if(user.isPresent()) {
			return  user.get();
		}
		return  null;
		
	}
	


	

}
