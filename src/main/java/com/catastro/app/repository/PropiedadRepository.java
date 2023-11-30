package com.catastro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catastro.app.model.Propiedad;

public interface PropiedadRepository extends JpaRepository<Propiedad, Integer>{
	

}
