package com.deveficiente.cadastro.novacategoria;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

	@NotBlank
	private String nome;

	public NovaCategoriaRequest() { }
	
	public NovaCategoriaRequest(@NotBlank String nome) {
		this.nome = nome;
	}
	
	public Categoria toModel() {
		return new Categoria(this.nome);
	}

	public String getNome() {
		return nome;
	}
}
