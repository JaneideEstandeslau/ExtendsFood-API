package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CozinhasModel")
@Data
public class CozinhasModelOpenApi {

	private CozinhaEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("CozinhaEmbeddedModel")
	@Data
	public class CozinhaEmbeddedModelOpenApi {

		private List<CozinhaModel> cozinhas;

	}

}