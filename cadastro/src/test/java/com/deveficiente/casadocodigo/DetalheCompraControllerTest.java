package com.deveficiente.casadocodigo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DetalheCompraControllerTest {

	@Autowired
	private CustomMockMvc mvc;
	
	@Test
	@DisplayName("exibe detalhes da compra corretamente")
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	void teste() throws Exception {
		
		mvc.post("/autores", Map.of("nome","edir","email","icetylucas@email.com","descricao","teste"));
		mvc.post("/categorias", Map.of("nome","comedia"));
		BigDecimal preco = new BigDecimal(20);
		mvc.post("/livros",Map.of("titulo", "titulo",
				"resumo", "resumo",
				"sumario", "sumario",
				"preco", preco,
				"numeroPaginas", 100,
				"isbn", "isbn",
				"dataPublicacao", LocalDateTime.now().plusDays(1)
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				"idCategoria", "1",
				"idAutor", "1"));
		
		String email = "email";
		String nome = "nome";
		String sobreNome = "sobreNome";
		String endereco = "endereco";
		String documento = "80183332032";
		String cidade = "cidade";
		String telefone = "14555412";
		String cep = "1000192";
		int quantidade = 10;		
		
		mvc.post("/paises", Map.of("nome","Brasil"));
		mvc.post("/estados", Map.of("nome","Ceara", "idPais","1"));
		
		HashMap<String, Object> dados = new HashMap<>();
		dados.put("email", email+"@gmail.com");
		dados.put("nome", nome);
		dados.put("sobreNome", sobreNome);
		dados.put("endereco", endereco);
		dados.put("documento", documento);
		dados.put("cidade", cidade);
		dados.put("idPais", "1");
		dados.put("idEstado", "1");
		dados.put("telefone", telefone);
		dados.put("cep", cep);
		List<Map<String, Object>> itens = List.of(Map.of("idLivro", "1", "quantidade",quantidade));
		Map<String, Object> pedido = Map.of("total", preco.multiply(new BigDecimal(quantidade)), "itens",itens);
		dados.put("pedido", pedido);
		
		mvc.post("/compras", dados);
			
		ResultActions resultado = mvc.get("/detalhes/1");
		
		Map<String, Object> pais = Map.of("nome","Brasil");
		Map<String, Object> estado = Map.of("nome","Ceara", "pais",pais);
		HashMap<String, Object> detalheCompra = new HashMap<>();
		detalheCompra.put("email", email+"@gmail.com");
		detalheCompra.put("nome", nome);
		detalheCompra.put("sobreNome", sobreNome);
		detalheCompra.put("endereco", endereco);
		detalheCompra.put("documento", documento);
		detalheCompra.put("cidade", cidade);
		detalheCompra.put("pais", pais);
		detalheCompra.put("estado", estado);
		detalheCompra.put("telefone", telefone);
		detalheCompra.put("cep", cep);
		dados.put("pedido", pedido);
		
		String jsonEsperado = new ObjectMapper()
				.writeValueAsString(detalheCompra);
		
		resultado.andExpect(MockMvcResultMatchers.content().json(jsonEsperado));
				
	}
}
