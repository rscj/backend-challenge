package com.invillia.exceptions;

public class DuplicatedNameException extends StoreException {

	private static final long serialVersionUID = 1L;

	public DuplicatedNameException(String message) {
		super(message);
	}
}