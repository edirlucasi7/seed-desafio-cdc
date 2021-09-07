package com.deveficiente.cadastro.detalhelivro;

import java.time.LocalDate;

import com.deveficiente.cadastro.novoautor.Autor;
import com.deveficiente.cadastro.novolivro.Livro;

public class DetalhesSiteLivroResponse {

	private String titulo;
	private String sumario;
	private String preco;
	private String numeroPaginas;
	private String isbn;
	private LocalDate dataPublicacao;
	private Autor autor;
	
	public DetalhesSiteLivroResponse(Livro livro) {
		super();
		this.titulo = livro.getTitulo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao();
		this.autor = livro.getAutor();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getSumario() {
		return sumario;
	}

	public String getPreco() {
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

	public Autor getAutor() {
		return autor;
	}
	
}
