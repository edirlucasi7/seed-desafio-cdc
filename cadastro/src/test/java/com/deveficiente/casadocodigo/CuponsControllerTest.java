package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.BigRange;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;
import net.jqwik.time.api.Dates;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class CuponsControllerTest {

	@Autowired
	private CustomMockMvc mvc;
	private static Set<String> unicos = new HashSet<>();
	
	@Property(tries = 10)
	@Label("fluxo de cadastro de cupom")
	void teste(@ForAll @NumericChars @StringLength(min = 1, max = 100) String codigo,
			@ForAll @BigRange(min = "1", max = "100") BigDecimal porcentagem,
			@ForAll("datasPresenteOuFuturas") LocalDate validade) throws Exception {
				
		Assumptions.assumeTrue(unicos.add(codigo));
		
		String validadeDataFormatada = validade
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		mvc.post("/cupons", Map.of("codigo",codigo,
				"porcentagem",porcentagem.toString(),
				"validade",validadeDataFormatada))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		mvc.post("/cupons", Map.of("codigo",codigo,
				"porcentagem",porcentagem.toString(),
				"validade",validadeDataFormatada))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		
	}
	
	@Provide
	Arbitrary<LocalDate> datasPresenteOuFuturas() {
	  return Dates.dates().atTheEarliest(LocalDate.now().plusDays(0));
	}
}
