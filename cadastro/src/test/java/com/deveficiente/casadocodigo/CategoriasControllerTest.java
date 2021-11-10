package com.deveficiente.casadocodigo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriasControllerTest {

	@Autowired
	private MockMvc mvc;
	private static Set<String> categorias = new HashSet<>();
	
	@Property(tries = 20)
	@Label("fluxo de cadastro de nova categoria")
	void teste(@ForAll @AlphaChars @StringLength(min = 1, max = 50) String nome) throws Exception {
		
		String payload = new ObjectMapper()
				.writeValueAsString(
						Map.of("nome", nome));
		
		Assumptions.assumeTrue(categorias.add(nome));
		
		MockHttpServletRequestBuilder conteudo = MockMvcRequestBuilders.post("/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload);
		
		mvc.perform(conteudo)
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		mvc.perform(conteudo)
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());

	}
	
	
}
