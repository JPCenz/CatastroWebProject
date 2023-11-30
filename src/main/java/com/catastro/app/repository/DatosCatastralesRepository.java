package com.catastro.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catastro.app.model.DatosCatastrales;
import com.catastro.app.model.Propiedad;

interface DatosCatastralesRepository extends JpaRepository<DatosCatastrales, Integer> {
	List<DatosCatastrales> findByPropiedadId(Integer id);
}
