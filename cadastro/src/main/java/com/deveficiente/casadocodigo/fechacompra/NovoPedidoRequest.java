package com.deveficiente.casadocodigo.fechacompra;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

public class NovoPedidoRequest {

	@NotNull
	@Positive
	@Min(1)
	private BigDecimal total;
	@Size(min = 1)
	private List<NovoCompraItemRequest> itens;
	
	public NovoPedidoRequest(@NotNull @Positive @Min(1) BigDecimal total,
			@Size(min = 1) List<NovoCompraItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "NovoCompraRequest [total=" + total + ", itens=" + itens + "]";
	}

	public Function<Compra,Pedido> toModel(EntityManager manager) {
		Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());
		
		return (compra) -> {
			Pedido pedido = new Pedido(compra, itensCalculados);
			Assert.isTrue(pedido.totalIgual(total), "o total enviado não corresponde ao total real");			
			return pedido;
		};		
		
	}

}
