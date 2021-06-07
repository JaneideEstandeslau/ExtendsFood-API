package com.digitalSystems.extendsfood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoModel {

	@ApiModelProperty(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg", position = 5)
	private String nomeArquivo;
	
	@ApiModelProperty(example = "Prime Rib ao ponto", position = 10)
	private String descricao;
	
	@ApiModelProperty(example = "image/jpeg", position = 15)
	private String contentType;
	
	@ApiModelProperty(example = "202912", position = 20)
	private Long tamanho;
	
}