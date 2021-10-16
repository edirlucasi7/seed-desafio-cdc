package com.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deveficiente.casadocodigo.compartilhado.ExistsId;
import com.deveficiente.casadocodigo.compartilhado.UniqueValue;
import com.deveficiente.casadocodigo.novacategoria.Categoria;
import com.deveficiente.casadocodigo.novoautor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovoRequestLivro {

	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	private @NotBlank String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	private @NotNull @Min(100) String numeroPaginas;
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private @NotBlank String isbn;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataPublicacao;
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private @NotNull Long idCategoria;
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private @NotNull Long idAutor;
	
	public NovoRequestLivro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) String numeroPaginas, @NotBlank String isbn,
			@NotNull Long idCategoria, @NotNull Long idAutor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}
	
	/*
	 * criei esse set porque o jackson está sendo incapaz de deserializar a data pelo construtor.
	*/
	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Livro toModel(EntityManager manager) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		Autor autor = manager.find(Autor.class, idAutor);
		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn,
				dataPublicacao, categoria, autor);
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Long getIdAutor() {
		return idAutor;
	}
	
}
