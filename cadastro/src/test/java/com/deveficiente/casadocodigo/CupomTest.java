package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;

public class CupomTest {

	@ParameterizedTest
	@CsvSource({
		"0, true",
		"1, true"
	})
	void teste1(long valor, boolean resultado) throws Exception {
		Cupom cupom = new Cupom("", BigDecimal.TEN, LocalDate.now().plusDays(valor));
		Assertions.assertEquals(resultado, cupom.cupomValido());
	}
	
	@Test
	@DisplayName("cria cupom com data que nao esta mais valida")
	void teste2() throws Exception {	
		Cupom cupom = new Cupom("", BigDecimal.TEN, LocalDate.now().plusDays(1));
		ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().minusDays(1));
		Assertions.assertFalse(cupom.cupomValido());
	}
	
	@Test
	@DisplayName("nao cria cupom com data de validade expirada")
	void teste3() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Cupom("", BigDecimal.TEN, LocalDate.now().minusDays(1));
		});
	}

}
