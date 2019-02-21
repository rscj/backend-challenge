package com.invillia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoreNotFoundException extends StoreException {
	
	private static final long serialVersionUID = 1L;
	
	public StoreNotFoundException(String message) {
		super(message);
	}
}