package com.catastro.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catastro.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	 Optional<Usuario> findByCorreo(String correo);

}
