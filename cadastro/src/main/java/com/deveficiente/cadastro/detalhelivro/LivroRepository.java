package com.deveficiente.cadastro.detalhelivro;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deveficiente.cadastro.novolivro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
