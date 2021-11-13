package com.deveficiente.casadocodigo.fechacompra;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.deveficiente.casadocodigo.compartilhado.ExistsId;
import com.deveficiente.casadocodigo.compartilhado.Generated;
import com.deveficiente.casadocodigo.cupomdesconto.Cupom;
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
	@ExistsId(domainClass = Cupom.class, fieldName = "codigo")
	private String codigoCupom;

	public NovaCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String cidade, @NotNull Long idPais,
			@NotBlank String telefone, @NotBlank String cep, @NotNull NovoPedidoRequest pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.cidade = cidade;
		this.idPais = idPais;
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

	public String getCodigoCupom() {
		return codigoCupom;
	}
	
	public Compra toModel(EntityManager manager, CupomRepository repository) {
		Pais pais = manager.find(Pais.class, idPais);
				
		Function<Compra,Pedido> funcaoConstrutorPedido = pedido.toModel(manager);
		
		Compra compra = new Compra(email, nome, sobreNome, documento, endereco, cidade,
				pais, telefone, cep, funcaoConstrutorPedido);
		if(StringUtils.hasText(codigoCupom)) {
			Cupom cupom = repository.findByCodigo(codigoCupom);
			compra.setCupom(cupom);			
		}
		if(idEstado!=null) {
			compra.setEstado(manager.find(Estado.class, idEstado));
		}
		
		return compra;
	}
	
	public Optional<String> temCupomDesconto() {
		return Optional.ofNullable(codigoCupom);
	}
	

	public Boolean temEstado() {
		return this.idEstado!=null;
	}

	@Override
	@Generated(Generated.ECLIPSE)
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
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);

	}

	public void setCodigoCupom(String codigo) {
		this.codigoCupom = codigo;
		
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}
	
}
