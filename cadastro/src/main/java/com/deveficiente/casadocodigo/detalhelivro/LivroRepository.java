package com.deveficiente.casadocodigo.detalhelivro;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deveficiente.casadocodigo.novolivro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
