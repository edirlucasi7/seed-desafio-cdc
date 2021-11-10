package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
import com.deveficiente.casadocodigo.fechacompra.NovaCompraRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoItemRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoRequest;
import com.deveficiente.casadocodigo.novacategoria.Categoria;
import com.deveficiente.casadocodigo.novoautor.Autor;
import com.deveficiente.casadocodigo.novolivro.Livro;
import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

public class NovaCompraRequestTest {

	CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
	EntityManager manager = Mockito.mock(EntityManager.class);

	
	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 5));
	private NovoPedidoRequest pedido = new NovoPedidoRequest(new BigDecimal("50"), itens);
	
	private Pais pais = new Pais("pais");	
	private Autor autor = new Autor("nome", "email@email.com", "descricao");
	private Categoria categoria = new Categoria("categoria");
	
	private Livro livro = new Livro("titulo", "resumo", "sumario", BigDecimal.TEN, 100, "97834985782",
				LocalDate.of(2000, 10, 10), categoria, autor);
	
	{
		Mockito.when(manager.find(Pais.class, 1l)).thenReturn(pais);
		
		Mockito.when(manager.find(Livro.class, 1l)).thenReturn(livro);
		
		Mockito.when(manager.find(Estado.class, 1l)).thenReturn(new Estado("estado", pais));
		
		Mockito.when(cupomRepository.findByCodigo("3RCV")).thenReturn(new Cupom("3RCV", BigDecimal.TEN,
				LocalDate.now().plusDays(1)));
	}
	
	private NovaCompraRequest request = new NovaCompraRequest("", "", "", "", "", "", 1l, "", "", pedido);
	
	@Test
	@DisplayName("cria compra com estado e cupom")
	void teste1() throws Exception {
		
		request.setCodigoCupom("3RCV");
		request.setIdEstado(1l);
		Compra novaCompra = request.toModel(manager, cupomRepository);
		
		Assertions.assertNotNull(novaCompra);
		Mockito.verify(manager).find(Estado.class, 1l);
		Mockito.verify(cupomRepository).findByCodigo("3RCV");	
		
	}
	
	@Test
	@DisplayName("cria compra com cupom e sem estado")
	void teste2() throws Exception {
		
		request.setCodigoCupom("3RCV");
		Compra novaCompra = request.toModel(manager, cupomRepository);
		Assertions.assertNotNull(novaCompra);
		Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
		Mockito.verify(cupomRepository).findByCodigo("3RCV");	
	}
	
	@Test
	@DisplayName("cria compra com estado e sem cupom")
	void teste3() throws Exception {
		
		request.setIdEstado(1l);
		Compra novaCompra = request.toModel(manager, cupomRepository);
		
		Assertions.assertNotNull(novaCompra);
		Mockito.verify(manager).find(Estado.class, 1l);
		Mockito.verify(cupomRepository, Mockito.never()).findByCodigo(Mockito.anyString());	
	}
	
	@ParameterizedTest
	@DisplayName("verifica documento valido")
	@CsvSource({
		"81213409004, true",
		"81813245000130, true",
		"839554300, false",
	})
	void teste4(String documento, boolean resultadoDocumento) throws Exception {
		NovaCompraRequest request = new NovaCompraRequest("", "", "", documento, "", "", 1l, "", "", pedido);
		
		Assertions.assertEquals(resultadoDocumento, request.documentoValido());
	}
	
}
