package com.invillia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedNameException extends StoreException {

	private static final long serialVersionUID = 1L;

	public DuplicatedNameException(String message) {
		super(message);
	}
}