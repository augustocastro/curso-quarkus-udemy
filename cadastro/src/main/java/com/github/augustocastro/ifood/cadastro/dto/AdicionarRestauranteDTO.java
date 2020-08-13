package com.github.augustocastro.ifood.cadastro.dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.github.augustocastro.ifood.cadastro.Restaurante;
import com.github.augustocastro.ifood.cadastro.infra.DTO;
import com.github.augustocastro.ifood.cadastro.infra.ValidDTO;

@ValidDTO
public class AdicionarRestauranteDTO implements DTO {

	@NotEmpty
	@NotNull
	public String proprietario;

	@Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$")
	@NotNull
	public String cnpj;

	@Size(min = 3, max = 30)
	public String nomeFantasia;

	public LocalizacaoDTO localizacao;

	@Override
	public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
		constraintValidatorContext.disableDefaultConstraintViolation();
		
		if (Restaurante.find("cnpj", cnpj).count() > 0) {
			constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
					.addPropertyNode("cnpj")
					.addConstraintViolation();
			
			return false;
		}
		
		return true;
	}
}
