package com.deveficiente.casadocodigo.novacategoria;

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
public class CategoriasController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/categorias")
	@Transactional
	public ResponseEntity<NovaCategoriaRequest> cria(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria novaCategoria = request.toModel();
		manager.persist(novaCategoria);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
}
