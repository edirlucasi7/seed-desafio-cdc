package com.deveficiente.casadocodigo.fechacompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;

@Component
public class CupomValidoValidator implements Validator{

	@Autowired
	private CupomRepository repository;
	
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
		
		Cupom cupom = repository.findByCodigo(request.getCodigoCupom());	
		if(StringUtils.hasText(request.getCodigoCupom())) {
			if(!cupom.cupomValido()) {
				errors.reject("idCupom", null, "a validade deste cupom esta expirada!");
			}	
		}
		
	}

}
