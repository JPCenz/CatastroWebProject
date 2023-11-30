package com.catastro.app.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "propiedad")
@Data
@NoArgsConstructor
public class Propiedad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891458010670160737L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "propiedad_seq")
	@SequenceGenerator(name = "propiedad_seq", sequenceName = "propiedad_seq", allocationSize = 1)
	@Column(name = "id")
	private Integer id;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name ="id_propiedad")
//	private Set<DatosGeoespaciales> datosGeoespaciales ;
	
	@Column(name="titulo_Propiedad")
	private String tituloPropiedad;
	
	@Column(name= "descripcion")
	private String descripcion;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "tipo_propiedad")
	private String tipoPropiedad;
	
	@Column(name ="estado_propiedad")
	private String estado;
	
	@UpdateTimestamp(source = SourceType.DB )
	@Column(name ="fecha_actualizacion")
	private Instant updateOn;
	
	@CreationTimestamp(source = SourceType.DB )
	@Column(name ="fecha_creacion")
	private Instant createOn;

}
