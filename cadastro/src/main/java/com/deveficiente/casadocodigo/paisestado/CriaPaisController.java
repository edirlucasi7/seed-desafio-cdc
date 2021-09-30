package com.deveficiente.casadocodigo.paisestado;

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
public class CriaPaisController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/paises")
	@Transactional
	public ResponseEntity<?> cria(@RequestBody @Valid NovoPaisRequest request) {
		Pais novoPais = new Pais(request.getNome());
		manager.persist(novoPais);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
}

