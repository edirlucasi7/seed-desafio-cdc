package com.deveficiente.casadocodigo.fechacompra;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;

@Component
public class CupomValidoValidator implements Validator{
	
	@PersistenceContext
	private EntityManager manager;
	
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
		
		Cupom cupom = manager.find(Cupom.class, request.getIdPais());
		
		LocalDate dataAtual = LocalDate.now();
		if(!dataAtual.isBefore(cupom.getValidade())) {
			errors.reject("idCupom", null, "a validade deste cupom esta expirada!");
		}
		
	}

}
