package com.deveficiente.casadocodigo.paisestado;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriaEstadoRequest {

	@Autowired
	private EntityManager manager;

	@PostMapping(value = "/estados")
	@Transactional
	public ResponseEntity<?> cria(@RequestBody @Valid NovoEstadoRequest request) {
		System.out.println(request);
		Estado novoEstado = request.toModel(manager);		
		manager.persist(novoEstado);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
}
