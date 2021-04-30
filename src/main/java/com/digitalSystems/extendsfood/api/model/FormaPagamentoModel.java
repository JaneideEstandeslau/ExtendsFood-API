package com.digitalSystems.extendsfood.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoModel {
	
	private Long id;
	
	private String descricao;
	
	private LocalDateTime dataAtualizacao;
}
