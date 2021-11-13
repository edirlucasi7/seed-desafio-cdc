package com.deveficiente.casadocodigo.cupomdesconto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuponsController {
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@PostMapping("/cupons")
	public String cria(@RequestBody @Valid NovoCupomRequest request) {
		Cupom novoCupom = request.toModel(); 
		manager.persist(novoCupom);
		return novoCupom.toString();
	}
	
}
