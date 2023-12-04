package com.catastro.app.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.catastro.app.model.Usuario;
import com.catastro.app.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Component
public class UserUtil {
	@Autowired
	static
	UsuarioRepository userRep;
	
	
	/*
	 * public static Usuario getCurrentUser(HttpSession session) { userRep = new
	 * UsuarioRepository(); var id = session.getAttribute("idusuario"); var actualId
	 * = id != null ? Integer.parseInt(id.toString()) : null; var user = actualId !=
	 * null ? userRep.findById(actualId) : null; if(user.isPresent()) { return
	 * user.get(); } return null;
	 * 
	 * }
	 */

}
