package com.catastro.app.model;

import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropiedadFormDTO {
	
	
	@NotBlank
	@Size(min = 3, message = "About Me must be between 10 and 200 characters")
	private String tituloPropiedad;
	
	@NotBlank
	@Size(min = 3, message = "About Me must be between 10 and 200 characters")
	private String descripcion;
	
	private String direccion;
	
	private String tipoPropiedad;
	
	private String estado;
	
	@Valid
	private DatoCatastralFormDTO datoCatastral =new DatoCatastralFormDTO();
	
	@NotNull
	private String stringWKT;
	

}
