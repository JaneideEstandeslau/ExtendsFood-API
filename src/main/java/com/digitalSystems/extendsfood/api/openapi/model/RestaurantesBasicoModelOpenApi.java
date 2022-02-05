package com.digitalSystems.extendsfood.api.openapi.model;
import java.util.List;

import org.springframework.hateoas.Links;

import com.digitalSystems.extendsfood.api.model.RestauranteBasicoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RestaurantesBasicoModel")
@Data
public class RestaurantesBasicoModelOpenApi {

	private RestaurantesBasicoModelEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("RestaurantesBasicoModelEmbeddedModel")
	@Data
	public class RestaurantesBasicoModelEmbeddedModelOpenApi {

		private List<RestauranteBasicoModel> restaurantes;

	}

}