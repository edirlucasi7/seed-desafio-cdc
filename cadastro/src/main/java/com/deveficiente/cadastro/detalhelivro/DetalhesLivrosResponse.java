package com.deveficiente.cadastro.detalhelivro;

import com.deveficiente.cadastro.novolivro.Livro;

public class DetalhesLivrosResponse {
	
	private Long id;
	private String nome;
	
	public DetalhesLivrosResponse(Livro livro) {
		super();
		this.id = livro.getId();
		this.nome = livro.getTitulo();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

}
