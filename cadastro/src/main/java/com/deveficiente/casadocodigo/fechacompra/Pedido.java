package com.deveficiente.casadocodigo.fechacompra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.deveficiente.casadocodigo.compartilhado.Generated;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Valid
	@OneToOne
	private Compra compra;
	@ElementCollection
	@Size(min = 1)
	private Set<ItemPedido> itens = new HashSet<>();
	
	@Deprecated
	public Pedido() {  }
	
	public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
		Assert.isTrue(!itens.isEmpty(), "todo pedido deve ter pelo menos um item!");
		this.compra = compra;
		this.itens.addAll(itens);
	}
	
	public boolean totalIgual(@Positive @NotNull BigDecimal total) {
		BigDecimal totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO,
				(atual, proximo) ->	atual.add(proximo));
			
		return totalPedido.doubleValue() == total.doubleValue();
	}
	
	public Long getId() {
		return id;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "Pedido [itens=" + itens + "]";
	}
	
}
