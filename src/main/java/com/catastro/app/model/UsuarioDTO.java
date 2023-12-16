package com.catastro.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	

	private Integer id;
	
	@NotBlank(message = "is required")

	private String nombres;
	
	@NotBlank(message = "is required")

	private String apellidoMaterno;
	
	@NotBlank(message = "is required")

	private String apellidoPaterno;
	
	@NotBlank(message = "is required")
	private String correo;
	
	@NotBlank(message = "is required")

	private String hashContrasena;
	
//	@NotBlank(message = "is required")
	@Column(name = "rol")
	private String rol;

}
