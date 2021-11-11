package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DetalheSiteLivroControllerTest {

	@Autowired
	private CustomMockMvc mvc;
	
	@Test
	@DisplayName("exibe detalhes do livro corretamente")
	void teste() throws Exception {
		
		mvc.post("/autores", Map.of("nome","edir", "email","icetylucas@email.com", "descricao","teste"));
		mvc.post("/categorias", Map.of("nome","comedia"));
		
		String titulo = "titulo";
		String resumo = "resumo";
		String sumario = "sumario";
		BigDecimal preco = new BigDecimal(100);
		int numeroPaginas = 100;
		String isbn = "12fr1";
		String dataPublicacaoFormatada = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/YYY"));
		
		mvc.post("/livros", Map.of("titulo",titulo, 
				"resumo",resumo,
				"sumario",sumario,
				"preco",preco,
				"numeroPaginas",numeroPaginas,
				"isbn",isbn,
				"dataPublicacao",dataPublicacaoFormatada,
				"idCategoria","1",
				"idAutor","1"));
		
		ResultActions resultado = mvc.get("/livros/1");
		
		Map<String, Object> autor = Map.of("nome","edir", "email","icetylucas@email.com", "descricao","teste");
		Map<String, Object> detalheLivro = Map.of("titulo",titulo, 
				"resumo",resumo,
				"sumario",sumario,
				"preco",preco,
				"numeroPaginas",numeroPaginas,
				"isbn",isbn,
				"dataPublicacao",dataPublicacaoFormatada,
				"autor", autor);
		
		String jsonEsperado = new ObjectMapper()
				.writeValueAsString(detalheLivro);
		resultado.andExpect(MockMvcResultMatchers.content().json(jsonEsperado));
		
	}
	
}
