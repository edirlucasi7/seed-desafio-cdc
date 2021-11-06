package com.deveficiente.casadocodigo.fechacompra;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;

@Component
public class CupomValidoValidator implements Validator{

	private CupomRepository repository;

	public CupomValidoValidator(CupomRepository repository) {
		super();
		this.repository = repository;
	}
	
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
		
		Optional<String> possivelCodigo = request.temCupomDesconto();
		if(possivelCodigo.isPresent()) {
			Cupom cupom = repository.findByCodigo(possivelCodigo.get());
			if(!cupom.cupomValido()) {
				errors.reject("idCupom", null, "a validade deste cupom esta expirada!");
			}	
		}
	
	}

}
