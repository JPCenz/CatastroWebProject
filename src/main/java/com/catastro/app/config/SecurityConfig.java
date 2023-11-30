package com.catastro.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.catastro.app.service.impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)// lo desactive por que no me deja trabajar en postman
//				.authorizeHttpRequests(
//						authorize -> authorize.requestMatchers("/admin", "/admin/save", "/contenido/save").permitAll()
//								.requestMatchers("src/main/resources/templates", "/users/login", "/css/**","/img/**").permitAll()
//								.anyRequest().authenticated())
//				.formLogin(form -> form.loginPage("/users/login").permitAll().defaultSuccessUrl("/users"))
//				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));
		 .authorizeHttpRequests(authorize-> authorize.anyRequest().permitAll());//Temporalmente deshabilito la configuracion de seguridad

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

}
