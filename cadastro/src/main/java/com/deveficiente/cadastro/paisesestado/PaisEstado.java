package com.deveficiente.cadastro.paisesestado;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class PaisEstado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "pais_id")
	private List<Estado> estados;
	
	public PaisEstado() { }
	
	public PaisEstado(@NotBlank String nomePais, List<Estado> estados) {
		super();
		this.nome = nomePais;
		this.estados = estados;
	}
	
}
