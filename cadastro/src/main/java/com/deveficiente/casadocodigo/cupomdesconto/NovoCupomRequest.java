package com.deveficiente.casadocodigo.cupomdesconto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigo.compartilhado.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

public class NovoCupomRequest {
	
	@NotNull
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	private String codigo;
	@NotNull
	@Positive
	private BigDecimal porcentagem;
	@NotNull
	@FutureOrPresent
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate validade;
	
	
	public NovoCupomRequest(@NotNull String codigo, @NotNull @Positive BigDecimal porcentagem) {
		this.codigo = codigo;
		this.porcentagem = porcentagem;
	}

	public Cupom toModel() {
		return new Cupom(codigo, porcentagem, validade);
	}

}
