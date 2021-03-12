package br.com.zup.oranges2.mercado.livre.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.oranges2.mercado.livre.produto.ProdutoDto;

public class ProibeNomeIgualDeCaracteristicaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()) {
			return;
		}
		ProdutoDto produtoDto = (ProdutoDto) target;
		if(produtoDto.temCaracteristicasIguais()) {
			errors.rejectValue("caracteristicas", null, "Característica já cadastrada");
			
		}
	}

}
