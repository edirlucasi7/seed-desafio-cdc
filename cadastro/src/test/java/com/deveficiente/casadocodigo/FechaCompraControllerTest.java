package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class FechaCompraControllerTest {

	@Autowired
	private CustomMockMvc mvc;

	@Property(tries = 10)
	@Label("fluxo de fechamento de compra")
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	void teste1(@ForAll @AlphaChars @StringLength(min = 1, max = 20) String email,
			@ForAll @AlphaChars @StringLength(min = 1, max = 100) String nome,
			@ForAll @AlphaChars @StringLength(min = 1, max = 100) String sobreNome,
			@ForAll @AlphaChars @StringLength(min = 1, max = 100) String endereco,
			@ForAll @AlphaChars @StringLength(min = 1, max = 100) String cidade,
			@ForAll @NumericChars @StringLength(min = 1, max = 100) String telefone,
			@ForAll @AlphaChars @StringLength(min = 1, max = 100) String cep, 
			@ForAll @IntRange(min = 1, max = 50) int quantidade) throws Exception {
		
		mvc.post("/autores", Map.of("nome","edir","email","icetylucas@email.com","descricao","teste"));
		mvc.post("/categorias", Map.of("nome","comedia"));
		BigDecimal preco = new BigDecimal("25");
		mvc.post("/livros",Map.of("titulo", "titulo",
				"resumo", "resumo",
				"sumario", "sumario",
				"preco", preco.toString(),
				"numeroPaginas", 100,
				"isbn", "isbn",
				"dataPublicacao", LocalDateTime.now().plusDays(1)
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				"idCategoria", "1",
				"idAutor", "1"));
		
		
		mvc.post("/paises", Map.of("nome","Brasil"));
		mvc.post("/estados", Map.of("nome","Ceara", "idPais","1"));
		
		HashMap<String, Object> dados = new HashMap<>();
		dados.put("email", email+"@gmail.com");
		dados.put("nome", nome);
		dados.put("sobreNome", sobreNome);
		dados.put("endereco", endereco);
		dados.put("documento", "60603258077");
		dados.put("cidade", cidade);
		dados.put("idPais", "1");
		dados.put("idEstado", "1");
		dados.put("telefone", telefone);
		dados.put("cep", cep);
		List<Map<String, Object>> itens = List.of(Map.of("idLivro", "1", "quantidade",quantidade));
		Map<String, Object> pedido = Map.of("total", preco.multiply(new BigDecimal(quantidade)), "itens",itens);
		dados.put("pedido", pedido);
		
		mvc.post("/compras", dados)
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
	}

}
