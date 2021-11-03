package com.deveficiente.casadocodigo;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.deveficiente.casadocodigo.novacategoria.Categoria;
import com.deveficiente.casadocodigo.novoautor.Autor;
import com.deveficiente.casadocodigo.novolivro.NovoLivroRequest;

public class NovoLivroRequestTest {

	private NovoLivroRequest request = new NovoLivroRequest("", "", "", BigDecimal.TEN, "", "", 1l, 1l);
	
	@Test
	@DisplayName("cria o livro com categoria e autor cadastrados")
	void teste1() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		
		Mockito.when(manager.find(Categoria.class, 1l))
			.thenReturn(new Categoria(""));
		
		Mockito.when(manager.find(Autor.class, 1l))
		.thenReturn(new Autor("", "", ""));
		
		Assertions.assertNotNull(request.toModel(manager));
	}

	@Test
	@DisplayName("cria o livro com categoria cadastrada e sem autor cadastrado")
	void teste2() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);	
		
		Mockito.when(manager.find(Categoria.class, 1l))
			.thenReturn(new Categoria(""));
		
		Mockito.when(manager.find(Autor.class, 1l))
			.thenReturn(null);
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			request.toModel(manager);
		});
	}
	
	@Test
	@DisplayName("cria o livro sem categoria cadastrada e com autor cadastrado")
	void teste3() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);	
		
		Mockito.when(manager.find(Categoria.class, 1l))
			.thenReturn(null);
		
		Mockito.when(manager.find(Autor.class, 1l))
			.thenReturn(new Autor("", "", ""));
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			request.toModel(manager);
		});
	}
	
}
