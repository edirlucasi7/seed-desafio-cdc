package com.deveficiente.cadastro.paisesestado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaisEstadoController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/pais-estados")
	@Transactional
	public ResponseEntity<PaisEstadoRequest> cria(@RequestBody @Valid PaisEstadoRequest request) {
		PaisEstado novoPaisEstados = request.toModelPaisEstados();
		manager.persist(novoPaisEstados);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
}
