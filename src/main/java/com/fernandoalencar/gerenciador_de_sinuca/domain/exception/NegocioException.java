package com.fernandoalencar.gerenciador_de_sinuca.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NegocioException(String message) {
		super(message);
	}

}
