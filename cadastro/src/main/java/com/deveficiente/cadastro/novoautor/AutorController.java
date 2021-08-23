package com.deveficiente.cadastro.novoautor;

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
public class AutorController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<?> cadastra(@RequestBody @Valid NovoAutorRequest request) {
		Autor autor = request.toModel();
		manager.persist(autor);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
