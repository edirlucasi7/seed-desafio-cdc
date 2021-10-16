package com.deveficiente.casadocodigo.cupomdesconto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String codigo;
	@NotNull
	@Positive
	private BigDecimal porcentagem;
	@NotNull
	@Future
	private LocalDate validade;
	
	@Deprecated
	public Cupom() {
		
	}
	
	public Cupom(@NotNull String codigo, @NotNull @Positive BigDecimal porcentagem, @NotNull @Future LocalDate validade) {
		this.codigo = codigo;
		this.porcentagem = porcentagem;
		this.validade = validade;
	}

	@Override
	public String toString() {
		return "Cupom [codigo=" + codigo + ", porcentagem=" + porcentagem + ", validade=" + validade + "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public boolean cupomValido() {
		LocalDate dataAtual = LocalDate.now(); 
		if(dataAtual.isBefore(validade)) {
			return true;
		}
		return false;
	}
	
}
