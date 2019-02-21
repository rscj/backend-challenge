package com.invillia.exceptions;

public class RequiredFieldException extends StoreException {

	private static final long serialVersionUID = 1L;

	public RequiredFieldException(String message) {
		super(message);
	}
}