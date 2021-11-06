package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;
import com.deveficiente.casadocodigo.fechacompra.CupomRepository;
import com.deveficiente.casadocodigo.fechacompra.CupomValidoValidator;
import com.deveficiente.casadocodigo.fechacompra.NovaCompraRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoItemRequest;
import com.deveficiente.casadocodigo.fechacompra.NovoPedidoRequest;

public class CupomValidoValidatorTest {
	
	private CupomRepository repository = Mockito.mock(CupomRepository.class);
	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 5));
	private NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(new BigDecimal("50"), itens);
	private NovaCompraRequest request = new NovaCompraRequest("", "", "", "", "", "", 1l, "", "", pedidoRequest);

	@Test
	@DisplayName("nao deveria passar caso tenha um codigo de cupom invalido")
	void teste1() throws Exception {
	
		Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().minusDays(1));
		
		request.setCodigoCupom("codigo");
		Mockito.when(repository.findByCodigo("codigo")).thenReturn(cupom);
		
		Errors errors = new BeanPropertyBindingResult(request, "target");
		CupomValidoValidator validator = new CupomValidoValidator(repository);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("a validade deste cupom esta expirada!", errors.getGlobalErrors().get(0).getDefaultMessage());
	
	}
	
	@Test
	@DisplayName("deveria passar caso tenha um codigo de cupom valido")
	void teste2() throws Exception {
	
		Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		
		request.setCodigoCupom("codigo");
		Mockito.when(repository.findByCodigo("codigo")).thenReturn(cupom);
		
		Errors errors = new BeanPropertyBindingResult(request, "target");
		CupomValidoValidator validator = new CupomValidoValidator(repository);
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	
	}
	
	@Test
	@DisplayName("deveria passar caso nao tenha codigo de cupom")
	void teste3() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");
		CupomValidoValidator validator = new CupomValidoValidator(repository);
		validator.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	
	}
	
	@Test
	@DisplayName("devevria parar caso já tenha erro")
	void teste4() throws Exception {
	
		Errors errors = new BeanPropertyBindingResult(request, "target");
		errors.reject("codigo");
		
		CupomValidoValidator validator = new CupomValidoValidator(repository);
		validator.validate(request, errors);
		
		Assertions.assertTrue(errors.hasErrors());
		Assertions.assertEquals("codigo", errors.getGlobalErrors().get(0).getCode());
	
	}
	
}
