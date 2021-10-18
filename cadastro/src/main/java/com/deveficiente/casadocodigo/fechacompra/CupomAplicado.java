package com.deveficiente.casadocodigo.fechacompra;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;

@Embeddable
public class CupomAplicado {

	@ManyToOne
	@NotNull
	private Cupom cupom;
	@Positive
	@NotNull
	private BigDecimal percentualDescontoMomento;
	@Future
	@NotNull
	private LocalDate localDataMomento;
	
	public CupomAplicado() {  }
	
	public CupomAplicado(@NotNull Cupom cupom) {
		super();
		this.cupom = cupom;
		this.percentualDescontoMomento = cupom.getPorcentagem();
		this.localDataMomento = cupom.getValidade();
	}

	@Override
	public String toString() {
		return "CupomAplicado [cupom=" + cupom + ", percentualDescontoMomento=" + percentualDescontoMomento
				+ ", localDataMomento=" + localDataMomento + "]";
	}
	
}
