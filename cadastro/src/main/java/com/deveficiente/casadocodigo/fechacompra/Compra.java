package com.deveficiente.casadocodigo.fechacompra;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;
import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@ManyToOne
	private Pais pais;
	@ManyToOne
	private Estado estado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;
	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Pedido pedido;
	@ManyToOne
	private Cupom cupom;

	public Compra(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobreNome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String cidade, Pais pais,
			@NotBlank String telefone, @NotBlank String cep, Function<Compra, Pedido> funcaoConstrutorPedido) {
		this.email = email;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.documento = documento;
		this.endereco = endereco;
		this.cidade = cidade;
		this.pais = pais;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = funcaoConstrutorPedido.apply(this);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome + ", sobreNome=" + sobreNome + ", documento="
				+ documento + ", endereco=" + endereco + ", cidade=" + cidade + ", pais=" + pais + ", estado=" + estado
				+ ", telefone=" + telefone + ", cep=" + cep + ", pedido=" + pedido + ", cupom=" + cupom + "]";
	}

	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.isTrue(estado.perteceAPais(pais), "o estado não pertecnce ao pais");
		this.estado = estado;
	}
	
	public void setCupom(@NotNull @Valid Cupom cupom) {
		Assert.isTrue(cupom.cupomValido(), "o cupom passado tem data de validade expirada");
		this.cupom = cupom;
	}

}
