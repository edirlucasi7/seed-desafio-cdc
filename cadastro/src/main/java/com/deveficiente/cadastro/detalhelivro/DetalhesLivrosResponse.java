package com.deveficiente.cadastro.detalhelivro;

public class DetalhesLivrosResponse {
	
	private Long id;
	private String nome;
	
	public DetalhesLivrosResponse(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

}
