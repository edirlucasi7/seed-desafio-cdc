package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.deveficiente.casadocodigo.fechacompra.EstadoPertenceAPaisValidator;
import com.deveficiente.casadocodigo.fechacompra.NovaCompraRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoItemRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoRequest;
import com.deveficiente.casadocodigo.paisestado.Estado;
import com.deveficiente.casadocodigo.paisestado.Pais;

public class EstadoPertencePaisValidatorTest {

	private EntityManager manager = Mockito.mock(EntityManager.class);
	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 5));
	private NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(new BigDecimal("50"), itens);
	private NovaCompraRequest request = new NovaCompraRequest("", "", "", "", "", "", 1l, "", "", pedidoRequest);
	
	
	@Test
	@DisplayName("nao devevria passar caso tenha estado que nao pertece ao pais")
	void teste1() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");

		Pais paisBrasil = new Pais("Brasil");
		Pais paisPortugal = new Pais("Potugal");
		Estado estado = new Estado("Ceara", paisBrasil);
		ReflectionTestUtils.setField(estado, "pais", paisPortugal);
		
		request.setIdEstado(1);
	
		Mockito.when(manager.find(Pais.class, request.getIdPais())).thenReturn(paisBrasil);
		Mockito.when(manager.find(Estado.class, request.getIdEstado())).thenReturn(estado);
		
		EstadoPertenceAPaisValidator validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("este estado não pertence ao pais informado!", errors.getGlobalErrors().get(0).getDefaultMessage());
		
	}
	
	@Test
	@DisplayName("devevria passar caso tenha estado que pertece ao pais")
	void teste2() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");

		Pais pais = new Pais("Brasil");
		Estado estado = new Estado("Ceara", pais);
	
		Mockito.when(manager.find(Pais.class, request.getIdPais())).thenReturn(pais);
		Mockito.when(manager.find(Estado.class, request.getIdEstado())).thenReturn(estado);
		
		EstadoPertenceAPaisValidator validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
		Assertions.assertEquals(true, estado.perteceAPais(pais));
		
	}
	
	@Test
	@DisplayName("devevria passar caso nao tenha id de um estado")
	void teste3() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");
		EstadoPertenceAPaisValidator validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	
	}
	
	@Test
	@DisplayName("devevria parar caso já tenha erro")
	void teste4() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");
		errors.reject("estado");
		
		EstadoPertenceAPaisValidator validator = new EstadoPertenceAPaisValidator(manager);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("estado", errors.getGlobalErrors().get(0).getCode());
	
	}
}
