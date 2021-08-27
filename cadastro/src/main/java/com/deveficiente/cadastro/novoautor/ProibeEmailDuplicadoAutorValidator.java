package com.deveficiente.cadastro.novoautor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator{

	@Autowired
	private AutorRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return NovoAutorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		NovoAutorRequest request = (NovoAutorRequest) target;
		Optional<Autor> possivelAutor = repository.findByEmail(request.getEmail());
		
		if(possivelAutor.isPresent()) {
			errors.rejectValue("email", null, "Já existe um email igual atrelado a outro(a) autor(a)!");
		}
	}
	
}
