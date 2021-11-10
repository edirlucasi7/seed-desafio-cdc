package com.deveficiente.casadocodigo.detalhelivro;

import com.deveficiente.casadocodigo.novolivro.Livro;

public class DetalheLivroResponse {
	
	private Long id;
	private String nome;
	
	public DetalheLivroResponse(Livro livro) {
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
