package com.anil.boot.exception;

public class ResourceNotfoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotfoundException() {
		super();
	}

	public ResourceNotfoundException(final String message) {
		super(message);
	}

}
