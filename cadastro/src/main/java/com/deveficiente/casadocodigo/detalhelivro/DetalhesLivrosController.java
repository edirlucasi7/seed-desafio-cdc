package com.deveficiente.casadocodigo.detalhelivro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.casadocodigo.novolivro.Livro;

@RestController
public class DetalhesLivrosController {

	@Autowired
	private LivroRepository repository;
	
	@GetMapping(value = "/livros/detalhes")
	public ResponseEntity<List<DetalhesLivrosResponse>> executa() {
		List<Livro> livrosCadastrados = repository.findAll();
				
		List<DetalhesLivrosResponse> detalhesLivrosResponse = new ArrayList<>();
		for (Livro livro : livrosCadastrados) {
			DetalhesLivrosResponse livroResponse = new DetalhesLivrosResponse(livro);
			detalhesLivrosResponse.add(livroResponse);
		}
		
		return new ResponseEntity<>(detalhesLivrosResponse, HttpStatus.OK);
	}
	
}
