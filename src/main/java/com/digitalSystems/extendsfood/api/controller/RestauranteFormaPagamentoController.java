package com.digitalSystems.extendsfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalSystems.extendsfood.api.ExtendsLinks;
import com.digitalSystems.extendsfood.api.assembler.FormaPagamentoModelAssembler;
import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;
import com.digitalSystems.extendsfood.api.openapi.controller.RestauranteFormaPagamentoControllerOpenApi;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private ExtendsLinks extendsLinks;

	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@GetMapping
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

		CollectionModel<FormaPagamentoModel> formasPagamentoModel = formaPagamentoModelAssembler
				.toCollectionModel(restaurante.getFormasPagamento()).removeLinks()
				.add(extendsLinks.linkToRestauranteFormasPagamento(restauranteId))
				.add(extendsLinks.linkToRestauranteFormasPagamentoAssociar(restauranteId, "associar"));

		formasPagamentoModel.getContent().forEach(formaPagamentoModel -> {
			formaPagamentoModel.add(extendsLinks.linkToRestauranteFormasPagamentoDesassociacao(restauranteId,
					formaPagamentoModel.getId(), "desassociar"));
		});

		return formasPagamentoModel;
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		restauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
		
		return ResponseEntity.noContent().build();
	}

}