package com.catastro.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "my_entity_seq", allocationSize = 1)
	@Column(name = "id_usuario")
	private Integer id;
	
	@NotBlank(message = "is required")
	@Column
	private String nombres;
	
	@NotBlank(message = "is required")
	@Column(name = "apellido_mat")
	private String apellidoMaterno;
	
	@NotBlank(message = "is required")
	@Column(name = "apellido_pat")
	private String apellidoPaterno;
	
	@NotBlank(message = "is required")
	@Column(name = "correo_electronico")
	private String correo;
	
	@NotBlank(message = "is required")
	@Column(name = "hash_contrasena")
	private String hashContrasena;
	
//	@NotBlank(message = "is required")
	@Column(name = "rol")
	private String rol;
	
	

}
