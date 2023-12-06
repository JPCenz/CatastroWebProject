package com.catastro.app.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatoCatastralFormDTO {

	@NotBlank
	@Size(min = 3, message = "Numero Catastral")
	private String numeroCatastral;

	@NotNull
	private BigDecimal valorTerreno;
	@NotNull
	private BigDecimal valorConstruccion;
	@NotNull
	private BigDecimal valorTotal;
	@NotNull
	private Integer anioConstruccion;
	@NotNull
	private BigDecimal metrosCuadrados;

}
