package com.deveficiente.cadastro.novacategoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeNomeCategoriaDuplicadoValidator implements Validator{

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		NovaCategoriaRequest request = (NovaCategoriaRequest) target;
		Optional<Categoria> possivelCategoria = repository.findByNome(request.getNome());
		
		if(possivelCategoria.isPresent()) {
			errors.rejectValue("nome", null, "Já existe uma categoria cadastrada com esse nome!");
		}
	}

}
