package com.digitalSystems.extendsfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitalSystems.extendsfood.api.model.DiaSemanaModel;
import com.digitalSystems.extendsfood.domain.model.DiaSemana;

@Component
public class DiaSemanaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public DiaSemanaModel toModel(DiaSemana diaSemana) {
		return modelMapper.map(diaSemana, DiaSemanaModel.class);
	}
	
	public List<DiaSemanaModel> toCollectionModel(List<DiaSemana> diasSemana){
		return diasSemana.stream()
				.map(diaSemana -> toModel(diaSemana))
				.collect(Collectors.toList());
	}
}
