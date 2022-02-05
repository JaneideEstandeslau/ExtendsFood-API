package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;
import com.digitalSystems.extendsfood.api.model.CidadeModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelOpenApi {

	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {

		private List<CidadeModel> cidades;

	}

}