package com.github.augustocastro.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.augustocastro.ifood.cadastro.Prato;


@Mapper(componentModel = "cdi")
public interface PratoMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "restaurante", ignore = true)
	public Prato toPrato(AdicionarPratoDTO dto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "nome", ignore = true)
	@Mapping(target = "descricao", ignore = true)
	@Mapping(target = "restaurante", ignore = true)
	public void toPrato(AtualizarPratoDTO dto, @MappingTarget Prato prato);
	
	@Mapping(target = "restaurante.nomeFantasia", source = "restaurante.nome")
	public PratoDTO toPratoDTO(Prato prato);
}
