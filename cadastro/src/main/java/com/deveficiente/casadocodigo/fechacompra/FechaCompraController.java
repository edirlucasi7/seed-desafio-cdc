package com.deveficiente.casadocodigo.fechacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechaCompraController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private CupomRepository repository;
	@Autowired
	private VerificaDocumentoCpfCnpjValidator verificaDocumentoCpfCnpjValidator;
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	@Autowired
	private CupomValidoValidator cupomValidoValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(verificaDocumentoCpfCnpjValidator, estadoPertenceAPaisValidator, cupomValidoValidator);
	}

	@Transactional
	@PostMapping(value = "/compras")
	public String executa(@RequestBody @Valid NovaCompraRequest request) {
		Compra compra = request.toModel(manager,repository);
		manager.persist(compra);
		return compra.toString();
	}
}
