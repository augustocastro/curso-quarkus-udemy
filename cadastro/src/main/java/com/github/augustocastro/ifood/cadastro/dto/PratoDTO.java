package com.github.augustocastro.ifood.cadastro.dto;

import java.math.BigDecimal;

public class PratoDTO {

	public Long id;
	
	public String nome;

	public String descricao;

	public BigDecimal preco;
	
	public RestauranteDTO restaurante;
}
