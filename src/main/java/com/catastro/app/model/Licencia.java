package com.catastro.app.model;

import java.math.BigDecimal;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "licencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Licencia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "licencia_seq")
	@SequenceGenerator(name = "licencia_seq", sequenceName = "licencia_seq", initialValue = 1000, allocationSize = 1)
	@Column(name = "id")
	private Integer id;

	@Column(name = "num_licencia" ,length = 20)
	private String numLicencia;

	@Column(name = "tipo_licencia")
	private String tipoLicencia;

	@Column(name = "estado_licencia")
	private String estadoLicencia;

	@Column(name = "descripcion")
	private String descripcion;
	
	@UpdateTimestamp(source = SourceType.DB )
	@Column(name ="fecha_actualizacion")
	private Instant updateOn;
	
	@CreationTimestamp(source = SourceType.DB )
	@Column(name ="fecha_creacion")
	private Instant createOn;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad" ,foreignKey = @ForeignKey(name = "fk_propiedad_licencia"))
	private Propiedad propiedad;

}
