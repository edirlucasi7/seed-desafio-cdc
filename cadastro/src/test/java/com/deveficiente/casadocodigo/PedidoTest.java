package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;
import com.deveficiente.casadocodigo.fechacompra.Compra;
import com.deveficiente.casadocodigo.fechacompra.CupomRepository;
import com.deveficiente.casadocodigo.fechacompra.ItemPedido;
import com.deveficiente.casadocodigo.fechacompra.NovaCompraRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoItemRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoRequest;
import com.deveficiente.casadocodigo.fechacompra.Pedido;
import com.deveficiente.casadocodigo.novacategoria.Categoria;
import com.deveficiente.casadocodigo.novoautor.Autor;
import com.deveficiente.casadocodigo.novolivro.Livro;
import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

public class PedidoTest {

	CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
	EntityManager manager = Mockito.mock(EntityManager.class);


	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 5));
	private NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(new BigDecimal("50"), itens);

	private Pais pais = new Pais("pais");	
	private Autor autor = new Autor("nome", "email@email.com", "descricao");
	private Categoria categoria = new Categoria("categoria");

	private Livro livro = new Livro("titulo", "resumo", "sumario", BigDecimal.TEN, 100, "97834985782",
			LocalDate.of(2000, 10, 10), categoria, autor);

	private NovaCompraRequest requestCompra = new NovaCompraRequest("", "", "", "", "", "", 1l, "", "", pedidoRequest);

	{
		
		Mockito.when(manager.find(Pais.class, 1l)).thenReturn(pais);

		Mockito.when(manager.find(Livro.class, 1l)).thenReturn(livro);

		Mockito.when(manager.find(Estado.class, 1l)).thenReturn(new Estado("estado", pais));

		Mockito.when(cupomRepository.findByCodigo("3RCV")).thenReturn(new Cupom("3RCV", BigDecimal.TEN,
				LocalDate.now().plusDays(1)));
		
	}

	@Test
	@DisplayName("cria pedido com itens")
	void teste1() throws Exception {	
		
		Compra novaCompra = requestCompra.toModel(manager, cupomRepository);
		Set<ItemPedido> itensPedido = Set.of(new ItemPedido(livro, 2));
		Pedido novoPedido = new Pedido(novaCompra, itensPedido);
		
		Assertions.assertNotNull(novoPedido);

	}
	
	@Test
	@DisplayName("nao cria pedido sem pelo menos um item")
	void teste2() throws Exception {	
		
		Compra novaCompra = requestCompra.toModel(manager, cupomRepository);	
		Set<ItemPedido> itensPedido = Set.of();
	
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Pedido(novaCompra, itensPedido);
		});
	}
	
	@Test
	@DisplayName("verifica a igualdade do valor total do pedido que chegou na request e o valor calculado pela api")
	void teste3() throws Exception {
			
		Compra novaCompra = requestCompra.toModel(manager, cupomRepository);	
		
		Set<ItemPedido> itemPedido = Set.of(new ItemPedido(livro, 5));
		Pedido pedido = new Pedido(novaCompra, itemPedido);
		
		Assertions.assertTrue(pedido.totalIgual(pedidoRequest.getTotal()));
		
	}
	
	@Test
	@DisplayName("verifica a disparidade do valor total do pedido que chegou na request e o valor calculado pela api")
	void teste4() throws Exception {
		
		NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(new BigDecimal("35"), itens);
			
		Compra novaCompra = requestCompra.toModel(manager, cupomRepository);	
		
		Set<ItemPedido> itemPedido = Set.of(new ItemPedido(livro, 5));
		Pedido pedido = new Pedido(novaCompra, itemPedido);
		
		Assertions.assertFalse(pedido.totalIgual(pedidoRequest.getTotal()));
		
	}
	
	@ParameterizedTest
	@DisplayName("verifica se o totaldo pedido é igual ao passado como argumento")
	@CsvSource({
		"50, true",
		"49.9, false",
		"50.1, false",
	})
	void teste5(BigDecimal valor, boolean resultadoEsperado) throws Exception {
		
		Compra novaCompra = requestCompra.toModel(manager, cupomRepository);	
		
		Set<ItemPedido> itemPedido = Set.of(new ItemPedido(livro, 5));
		Pedido pedido = new Pedido(novaCompra, itemPedido);
		
		Assertions.assertEquals(resultadoEsperado, pedido.totalIgual(valor));
		
	}
	
}
