package com.digitalSystems.extendsfood.api.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoModel extends RepresentationModel<ProdutoModel>{

	@ApiModelProperty(example = "1", position = 5)
	private Long id;
	
	@ApiModelProperty(example = "Pizza de calabresa", position = 10)
	private String nome; 
	
	@ApiModelProperty(example = "Deciliosa pizza de calabresa", position = 15)
	private String descricao;
	
	@ApiModelProperty(example = "30.00", position = 20)
	private BigDecimal preco;
	
	@ApiModelProperty(example = "true", position = 25)
	private Boolean disponivel;
	
	@ApiModelProperty(position = 30)
	private CategoriaProdutoResumoModel categoriaProduto;
	
	@ApiModelProperty(position = 35)
	private List<ComplementoModel> complementos;
	
	@ApiModelProperty(position = 40)
	private List<DiaSemanaModel> diasDisponiveis;
}
