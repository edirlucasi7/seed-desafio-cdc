package com.deveficiente.casadocodigo.detalhecompra;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deveficiente.casadocodigo.fechacompra.Compra;

@RestController
public class DetalheCompraController {

	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/detalhes/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {
		Compra compra = manager.find(Compra.class, id);
		
		Optional.of(compra).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		DetalheCompraResponse detalhe = new DetalheCompraResponse(compra);
		return new ResponseEntity<>(detalhe, HttpStatus.OK);
	}
}
