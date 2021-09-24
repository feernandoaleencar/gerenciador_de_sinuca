package com.fernandoalencar.gerenciador_de_sinuca.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente. ");
		
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}
