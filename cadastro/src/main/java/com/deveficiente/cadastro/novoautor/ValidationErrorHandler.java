package com.deveficiente.cadastro.novoautor;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ValidationErrorHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<Object>(new ExceptionError("Verifique se nenhum campo está nulo ou em branco!"), HttpStatus.BAD_REQUEST);
	}
	
	private class ExceptionError implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String descricaoError;
		
		ExceptionError(String descricao) {
			this.descricaoError = descricao;
		}

		@SuppressWarnings("unused")
		public String getDescricao() {
			return descricaoError;
		}
	
	}
	
}
