package com.deveficiente.casadocodigo.fechacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

@Component
public class EstadoPertenceAPaisValidator implements Validator{

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
		
		Pais pais = manager.find(Pais.class, request.getIdPais());
		Estado estado = manager.find(Estado.class, request.getIdEstado());
		
		if(!estado.perteceAPais(pais)) {
			errors.reject("idEstado", null, "este estado não pertence ao pais informado!");
		}
	}

	
	
}
