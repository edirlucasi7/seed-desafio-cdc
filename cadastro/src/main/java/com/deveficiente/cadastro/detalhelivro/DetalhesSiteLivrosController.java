package com.deveficiente.cadastro.detalhelivro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.cadastro.novolivro.Livro;

@RestController
public class DetalhesSiteLivrosController {
	
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/livros/{id}")
	public ResponseEntity<DetalhesSiteLivroResponse> busca(@PathVariable("id") Long id) {
		Livro possivelLivro = manager.find(Livro.class, id);
		
		if(possivelLivro == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		DetalhesSiteLivroResponse livroResponse = new DetalhesSiteLivroResponse(possivelLivro);
		return new ResponseEntity<>(livroResponse, HttpStatus.OK);
	}	
	
}
