package com.deveficiente.casadocodigo.novacategoria;

import javax.validation.constraints.NotBlank;

import com.deveficiente.casadocodigo.novoautor.compartilhado.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
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
