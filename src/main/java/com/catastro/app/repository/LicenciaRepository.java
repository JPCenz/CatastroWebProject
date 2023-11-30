package com.catastro.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.catastro.app.model.Licencia;
import com.catastro.app.model.Propiedad;

public interface LicenciaRepository extends JpaRepository<Licencia, Integer>{
	List<Licencia> findByPropiedadId(Integer id);
}
