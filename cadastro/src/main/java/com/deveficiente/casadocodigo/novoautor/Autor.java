package com.deveficiente.casadocodigo.novoautor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@Email @Column(unique = true) private @NotBlank String email;
	@NotBlank @Size(max = 400) private String descricao;
	private @NotNull LocalDateTime instanteRegistro;
	
	@Deprecated
	public Autor() { }
	
	public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
		this.instanteRegistro = LocalDateTime.now();
	}	
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
