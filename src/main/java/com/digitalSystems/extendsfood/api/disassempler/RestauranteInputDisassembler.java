package com.digitalSystems.extendsfood.api.disassempler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.inputEntidade.RestauranteInput;
import com.digitalSystems.extendsfood.domain.model.Cidade;
import com.digitalSystems.extendsfood.domain.model.Cozinha;
import com.digitalSystems.extendsfood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.digitalSystems.extendsfood.domain.model.Cidade was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		restaurante.getEndereco().setCidade(new Cidade());
		
		modelMapper.map(restauranteInput, restaurante);
	}
}