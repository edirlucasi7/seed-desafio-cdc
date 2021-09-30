package com.deveficiente.casadocodigo.paisestado;

import javax.validation.constraints.NotBlank;

import com.deveficiente.casadocodigo.novoautor.compartilhado.UniqueValue;

public class NovoPaisRequest {

	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;

	public String getNome() {
		return nome;
	}

}
