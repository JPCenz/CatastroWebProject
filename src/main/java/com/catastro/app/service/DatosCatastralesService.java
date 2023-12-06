package com.catastro.app.service;

import com.catastro.app.model.DatoCatastralFormDTO;
import com.catastro.app.model.DatosCatastrales;

public interface DatosCatastralesService {
	
	DatosCatastrales fromDTO(DatoCatastralFormDTO dto);

}
