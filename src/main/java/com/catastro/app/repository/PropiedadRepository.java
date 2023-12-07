package com.catastro.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catastro.app.model.Propiedad;

public interface PropiedadRepository extends JpaRepository<Propiedad, Integer>{
	List<Propiedad> findByIdContaining(String id);

}
