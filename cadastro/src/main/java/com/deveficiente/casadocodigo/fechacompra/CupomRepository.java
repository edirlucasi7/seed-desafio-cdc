package com.deveficiente.casadocodigo.fechacompra;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deveficiente.casadocodigo.cupomdesconto.Cupom;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Long>{

	public Cupom findByCodigo(String codigo);
}
