package com.deveficiente.casadocodigo.fechacompra;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificaDocumentoCpfCnpjValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		NovaCompraRequest request = (NovaCompraRequest) target;
		if(!request.documentoValido()) {
			errors.reject("documento", null, "documento precisa ser cpf ou cnpj");
		}
	}

}
