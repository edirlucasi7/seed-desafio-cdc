package com.deveficiente.cadastro.detalhelivro;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.cadastro.novolivro.Livro;

@RestController
public class DetalhesSiteLivrosController {
	
	@Autowired
	private LivroRepository repository;

	@GetMapping(value = "/livros/{id}")
	public ResponseEntity<DetalhesSiteLivrosResponse> busca(@PathVariable("id") Long id) {
		Optional<Livro> possivelLivro = repository.findById(id);
		
		if(!possivelLivro.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		DetalhesSiteLivrosResponse livroResponse = new DetalhesSiteLivrosResponse(possivelLivro.get());
		return new ResponseEntity<>(livroResponse, HttpStatus.OK);
	}	
	
}
