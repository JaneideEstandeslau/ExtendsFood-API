package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.EstadoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("EstadosModel")
@Data
public class EstadosModelOpenApi {

	private EstadoEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("EstadosEmbeddedModel")
	@Data
	public class EstadoEmbeddedModelOpenApi {

		private List<EstadoModel> estados;

	}

}