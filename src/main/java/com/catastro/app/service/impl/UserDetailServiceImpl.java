package com.catastro.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.catastro.app.model.Usuario;
import com.catastro.app.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserDetailServiceImpl  implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;


	@Autowired
	private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUser = usuarioService.findByCorreo(username);
		System.out.println(username);
		if(optionalUser.isPresent()) {
			session.setAttribute("idusuario", optionalUser.get().getId());
			
			Usuario u  = optionalUser.get();
			return User.builder()
					.username(u.getCorreo())
					.password(u.getHashContrasena())
					.roles(u.getRol())
					.build();
			
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
	}

}
