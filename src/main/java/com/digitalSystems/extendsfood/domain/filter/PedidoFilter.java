package com.digitalSystems.extendsfood.domain.filter;


import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.digitalSystems.extendsfood.domain.model.enums.StatusPedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoFilter {

	@ApiModelProperty(example = "1", value = "ID do Produto para filtro da pesquisa")
	private Long produtoId;
	
	@ApiModelProperty(example = "1", value = "ID do Restaurante para filtro da pesquisa")
	private Long restauranteId;
	
	@ApiModelProperty(example = "ENTREGUE", value = "Status do Pedido")
	private StatusPedido status;
	
	@ApiModelProperty(example = "2021-06-01T20:00:00", value = "Data/hora de criação inicial para filtro da pesquisa")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoInicio;
	
	@ApiModelProperty(example = "2021-06-30T22:47:34", value = "Data/hora de criação final para filtro da pesquisa")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoFim;
	
}