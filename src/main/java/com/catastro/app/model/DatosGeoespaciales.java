package com.catastro.app.model;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import jakarta.persistence.CascadeType;
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
@Table(name = "datos_geoespaciales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatosGeoespaciales implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "datos_geoespaciales_seq")
    @SequenceGenerator(name = "datos_geoespaciales_seq", sequenceName = "datos_geoespaciales_seq", allocationSize = 1)
	@Column(name = "id")
	private Integer id;
	
	@Column(columnDefinition = "geometry(MULTIPOLYGON,4326)")
	private MultiPolygon poligono;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad" ,foreignKey = @ForeignKey(name = "fk_propiedad_datos geoespaciales"))
	private Propiedad propiedad;
	
	

}
