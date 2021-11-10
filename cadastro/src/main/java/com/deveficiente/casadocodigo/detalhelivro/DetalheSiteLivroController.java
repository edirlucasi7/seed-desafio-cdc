package com.deveficiente.casadocodigo.detalhelivro;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deveficiente.casadocodigo.novolivro.Livro;

@RestController
public class DetalheSiteLivroController {
	
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/livros/{id}")
	public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {
		Livro possivelLivro = manager.find(Livro.class, id);
		
		Optional.ofNullable(possivelLivro).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		DetalheSiteLivroResponse livroResponse = new DetalheSiteLivroResponse(possivelLivro);
		return new ResponseEntity<>(livroResponse, HttpStatus.OK);
	}
	
}
