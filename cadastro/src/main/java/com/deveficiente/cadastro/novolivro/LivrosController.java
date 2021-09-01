package com.deveficiente.cadastro.novolivro;

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
public class LivrosController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/livros/cadastrar")
	@Transactional
	public ResponseEntity<NovoRequestLivro> cria(@RequestBody @Valid NovoRequestLivro request) {
		Livro novoLivro = request.toModel(manager);
		manager.persist(novoLivro);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
}
