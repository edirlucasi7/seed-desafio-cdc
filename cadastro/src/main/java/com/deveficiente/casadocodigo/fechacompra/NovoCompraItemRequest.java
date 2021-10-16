package com.deveficiente.casadocodigo.fechacompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigo.compartilhado.ExistsId;
import com.deveficiente.casadocodigo.novolivro.Livro;

public class NovoCompraItemRequest {

	@ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long idLivro;
	@Positive
	@Min(1)
	private int quantidade;

	public NovoCompraItemRequest(Long idLivro, @Positive @Min(1) int quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "NovoCompraItemRequest [idLivro=" + idLivro + ", quantidade=" + quantidade + "]";
	}

	public ItemPedido toModel(EntityManager manager) {
		@NotNull Livro livro = manager.find(Livro.class, idLivro);
		return new ItemPedido(livro, quantidade);
	}	

}
