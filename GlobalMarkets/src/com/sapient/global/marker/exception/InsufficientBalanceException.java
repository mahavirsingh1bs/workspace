package com.sapient.global.marker.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InsufficientBalanceException() { }
	
	public InsufficientBalanceException(String message) {
		super(message);
	}
	
	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InsufficientBalanceException(Throwable cause) {
		super(cause);
	}

}
