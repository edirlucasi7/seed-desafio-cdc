package com.deveficiente.casadocodigo.fechacompra;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigo.compartilhado.Generated;
import com.deveficiente.casadocodigo.novolivro.Livro;

@Embeddable
public class ItemPedido {

	@ManyToOne
	@NotNull
	private Livro livro;
	@Positive
	private int quantidade;
	@Positive
	private BigDecimal precoMomento;
	
	public ItemPedido() { }
	
	public ItemPedido(@NotNull Livro livro, @Positive @Min(1) int quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = livro.getPreco();
	}
	
	public BigDecimal total() {
		return precoMomento.multiply(new BigDecimal(quantidade));
	}

	public Livro getLivro() {
		return livro;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public BigDecimal getPrecoMomento() {
		return precoMomento;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		return result;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPedido [livro=" + livro + ", quantidade=" + quantidade + ", precoMomento=" + precoMomento + "]";
	}

}
