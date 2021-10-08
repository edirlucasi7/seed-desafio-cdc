package com.deveficiente.casadocodigo.fechacompra;

import java.util.function.Function;

import javax.persistence.EntityManager;
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
	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	@NotNull
	private NovoPedidoRequest pedido;

	public NovaCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String cidade, @NotNull Long idPais,
			Long idEstado, @NotBlank String telefone, @NotBlank String cep, @NotNull NovoPedidoRequest pedido) {
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
		this.pedido = pedido;
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

	public Compra toModel(EntityManager manager) {
		Pais pais = manager.find(Pais.class, idPais);

		Function<Compra,Pedido> funcaoConstrutorPedido = pedido.toModel(manager);
		
		Compra compra = new Compra(email, nome, sobreNome, documento, endereco, cidade,
				pais, telefone, cep, funcaoConstrutorPedido);
		if(idEstado!=null) {
			compra.setEstado(manager.find(Estado.class, idEstado));
		}
		
		return compra;
	}

	@Override
	public String toString() {
		return "NovaCompraRequest [email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome + ", documento="
				+ documento + ", endereco=" + endereco + ", cidade=" + cidade + ", idPais=" + idPais + ", idEstado="
				+ idEstado + ", telefone=" + telefone + ", cep=" + cep + ", pedido=" + pedido + "]";
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
