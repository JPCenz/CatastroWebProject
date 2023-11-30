package com.catastro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.catastro.app.model.DatosGeoespaciales;
import java.util.List;


public interface DatosGeoespacialesRepository extends JpaRepository<DatosGeoespaciales,Integer>{
	List<DatosGeoespaciales>findByPropiedadId(Integer id);

}
