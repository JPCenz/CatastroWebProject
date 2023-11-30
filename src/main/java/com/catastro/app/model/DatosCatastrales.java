package com.catastro.app.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "datos_catastrales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosCatastrales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "datos_catastrales_seq")
    @SequenceGenerator(name = "datos_catastrales_seq", sequenceName = "datos_catastrales_seq", initialValue = 1000, allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "numero_catastral")
	private String numeroCatastral;
	
	@Column(precision = 10, scale = 2,name = "valor_terreno")
	private BigDecimal valorTerreno;
	
	@Column(precision = 10, scale = 2,name = "valor_construccion")
	private BigDecimal valorConstruccion;
	
	@Column(precision = 10, scale = 2,name = "valor_total")
	private BigDecimal valorTotal;
	
	@Column(name = "ano_contruccion")
	private Integer anioConstruccion;
	
	@Column(precision = 10, scale = 2,name = "metros_cuadrados")
	private BigDecimal metrosCuadrados;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad" ,foreignKey = @ForeignKey(name = "fk_propiedad_datos_catastrales"))
	private Propiedad propiedad;
	
	
	
	
	

}
