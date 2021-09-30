package com.deveficiente.casadocodigo.fechacompra;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechaCompraController {
	
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new VerificaDocumentoCpfCnpjValidator(), estadoPertenceAPaisValidator);
	}

	@PostMapping(value = "/compras")
	public String executa(@RequestBody @Valid NovaCompraRequest request) {
		return request.toString();
	}
}
