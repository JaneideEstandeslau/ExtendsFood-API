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
import com.digitalSystems.extendsfood.api.assembler.PermissaoModelAssembler;
import com.digitalSystems.extendsfood.api.model.PermissaoModel;
import com.digitalSystems.extendsfood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.digitalSystems.extendsfood.core.security.CheckSecurity;
import com.digitalSystems.extendsfood.domain.model.Grupo;
import com.digitalSystems.extendsfood.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private ExtendsLinks extendsLinks;
	
	@Autowired
	private GrupoService grupoService;

	@Autowired
	private PermissaoModelAssembler permissaoAssembler;

	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);

		CollectionModel<PermissaoModel> permissoesModel = permissaoAssembler
				.toCollectionModel(grupo.getPermissoes())
				.removeLinks()
				.add(extendsLinks.linkToGrupoPermissao(grupoId))
				.add(extendsLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

		permissoesModel.getContent().forEach(permissaoModel -> {
			permissaoModel
					.add(extendsLinks.linkToGrupoPermissaoDesassociacao(grupoId, permissaoModel.getId(), "desassociar"));
		});

		return permissoesModel;
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.adicionarPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.removerPermissao(grupoId, permissaoId);

		return ResponseEntity.noContent().build();
	}
}
