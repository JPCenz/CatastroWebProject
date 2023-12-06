package com.catastro.app.service.impl;

import org.springframework.stereotype.Service;

import com.catastro.app.model.DatoCatastralFormDTO;
import com.catastro.app.model.DatosCatastrales;
import com.catastro.app.service.DatosCatastralesService;

@Service
public class DatosCatastralesServiceImpl implements DatosCatastralesService {

	
	public DatosCatastrales fromDTO(DatoCatastralFormDTO dto) {
		var d = new DatosCatastrales();
		d.setAnioConstruccion(dto.getAnioConstruccion());
		d.setMetrosCuadrados(dto.getMetrosCuadrados());
		d.setNumeroCatastral(dto.getNumeroCatastral());
		d.setValorConstruccion(dto.getValorConstruccion());
		d.setValorTerreno(dto.getValorTerreno());
		d.setValorTotal(dto.getValorTotal());
		return d;
	}

}
