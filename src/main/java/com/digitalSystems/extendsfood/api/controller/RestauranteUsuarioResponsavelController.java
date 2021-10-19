package com.digitalSystems.extendsfood.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import com.digitalSystems.extendsfood.api.assembler.UsuarioModelAssembler;
import com.digitalSystems.extendsfood.api.model.FormaPagamentoModel;
import com.digitalSystems.extendsfood.api.model.UsuarioModel;
import com.digitalSystems.extendsfood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.digitalSystems.extendsfood.domain.model.Restaurante;
import com.digitalSystems.extendsfood.domain.service.RestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private ExtendsLinks extendsLinks;
	
	@Autowired
	private RestauranteService restauranteService;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

		CollectionModel<UsuarioModel> usuariosModel = usuarioModelAssembler
				.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(extendsLinks.linkToRestauranteResponsaveis(restauranteId))
				.add(extendsLinks.linkToRestauranteResponsavelAssociacao(restauranteId));
		
		usuariosModel.getContent().forEach(usuarioModel -> {
			usuarioModel.add(extendsLinks.linkToRestauranteResponsavelDesassociacao(restauranteId, usuarioModel.getId()));
		});
		
		return usuariosModel;
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.desassociarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.associarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}