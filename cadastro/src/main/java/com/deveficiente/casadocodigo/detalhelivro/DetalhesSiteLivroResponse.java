package com.deveficiente.casadocodigo.detalhelivro;

import java.time.format.DateTimeFormatter;

import com.deveficiente.casadocodigo.novoautor.Autor;
import com.deveficiente.casadocodigo.novolivro.Livro;

public class DetalhesSiteLivroResponse {

	private String titulo;
	private String sumario;
	private String preco;
	private String numeroPaginas;
	private String isbn;
	private String dataPublicacao;
	private Autor autor;
	
	public DetalhesSiteLivroResponse(Livro livro) {
		super();
		this.titulo = livro.getTitulo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public Autor getAutor() {
		return autor;
	}
	
}
