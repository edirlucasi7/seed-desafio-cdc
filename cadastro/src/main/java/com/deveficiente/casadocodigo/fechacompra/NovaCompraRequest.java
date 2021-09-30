package com.deveficiente.casadocodigo.fechacompra;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import com.deveficiente.casadocodigo.novoautor.compartilhado.ExistsId;
import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

public class NovaCompraRequest {

	@Email @NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobreNome;
	@NotBlank
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;
	@NotNull
	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	
	public NovaCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String cidade, @NotNull Long idPais,
			@NotNull Long idEstado, @NotBlank String telefone, @NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
	}
	
	public String getDocumento() {
		return this.documento;
	}

	public Long getIdPais() {
		return this.idPais;
	}
	
	public Long getIdEstado() {
		return this.idEstado;
	}
	
	@Override
	public String toString() {
		return "NovaCompraRequest [email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome + ", documento="
				+ documento + ", endereco=" + endereco + ", cidade=" + cidade + ", idPais=" + idPais + ", idEstado="
				+ idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}
	
	public boolean documentoValido() {
		Assert.hasLength(documento, "não deve ser null");
		
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		
		CNPJValidator cnpjValidator = new CNPJValidator();
		cpfValidator.initialize(null);
		
		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	
	}

	
}
