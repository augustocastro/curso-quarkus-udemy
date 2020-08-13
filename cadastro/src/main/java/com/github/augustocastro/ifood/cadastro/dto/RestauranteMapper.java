package com.github.augustocastro.ifood.cadastro.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.augustocastro.ifood.cadastro.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

	@Mapping(target = "nome", source = "nomeFantasia")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)
	public Restaurante toRestaurante(AdicionarRestauranteDTO dto);

	@Mapping(target = "nome", source = "nomeFantasia")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "cnpj", ignore = true)
	@Mapping(target = "proprietario", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "dataAtualizacao", ignore = true)
	@Mapping(target = "localizacao.id", ignore = true)
	public void toRestaurante(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);

	@Mapping(target = "nomeFantasia", source = "nome")
	@Mapping(target = "dataCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
	public RestauranteDTO toRestauranteDTO(Restaurante restaurante);

}
