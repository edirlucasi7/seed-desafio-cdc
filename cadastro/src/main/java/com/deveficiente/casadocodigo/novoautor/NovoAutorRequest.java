package com.deveficiente.casadocodigo.novoautor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoAutorRequest {

	@NotBlank
	private String nome;
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	@NotBlank
	@Size(max = 400)
	private String descricao;
	private LocalDateTime instanteRegistro = LocalDateTime.now(); 

	public NovoAutorRequest(@NotBlank String nome, @NotBlank @Email String email,
			@NotBlank @Size(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}

	public String getEmail() {
		return this.email;
	}

}
