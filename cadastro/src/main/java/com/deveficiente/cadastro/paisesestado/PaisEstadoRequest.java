package com.deveficiente.cadastro.paisesestado;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.deveficiente.cadastro.novoautor.compartilhado.UniqueValue;

public class PaisEstadoRequest {

	@UniqueValue(domainClass = PaisEstado.class, fieldName = "nome")
	private @NotBlank String nomePais;
	private List<Estado> estados;

	public PaisEstadoRequest() { }
	
	public PaisEstadoRequest(@NotBlank String nomePais, List<Estado> estados) {
		super();
		this.nomePais = nomePais;
		this.estados = estados;
	}

	public PaisEstado toModelPaisEstados() {
		return new PaisEstado(this.nomePais, this.estados);
	}

	public String getNomePais() {
		return nomePais;
	}

	public List<Estado> getEstados() {
		return estados;
	}

}
