package com.deveficiente.casadocodigo.paisestado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.casadocodigo.compartilhado.ExistsId;
import com.deveficiente.casadocodigo.compartilhado.UniqueValue;

public class NovoEstadoRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Estado.class,fieldName = "nome")
	private String nome;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(EntityManager manager) {
		return new Estado(nome, manager.find(Pais.class, idPais));
	}

	public Long getIdPais() {
		return idPais;
	}

	public String getNome() {
		return nome;
	}	
	
}
