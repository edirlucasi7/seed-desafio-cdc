package com.deveficiente.casadocodigo.novolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deveficiente.casadocodigo.compartilhado.Generated;
import com.deveficiente.casadocodigo.novacategoria.Categoria;
import com.deveficiente.casadocodigo.novoautor.Autor;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	private @NotBlank String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	private @NotNull @Min(100) int numeroPaginas;
	private @NotBlank String isbn;
	@Future
	private @NotNull LocalDate dataPublicacao;
	@ManyToOne
	private @NotNull Categoria categoria;
	@ManyToOne
	private @NotNull Autor autor;
	
	public Livro() { }
	
	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotBlank String isbn,
			@Future @NotNull LocalDate dataPublicacao, @NotNull Categoria categoria, @NotNull Autor autor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}
	
	public Long getId() {
		return id;
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

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Autor getAutor() {
		return autor;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario + ", preco="
				+ preco + ", numeroPaginas=" + numeroPaginas + ", isbn=" + isbn + ", dataPublicacao=" + dataPublicacao
				+ ", categoria=" + categoria + ", autor=" + autor + "]";
	}

}
