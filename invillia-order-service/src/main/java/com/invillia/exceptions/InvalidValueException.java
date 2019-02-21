package com.invillia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValueException extends OrderException {

	private static final long serialVersionUID = 1L;

	public InvalidValueException(String message) {
		super(message);
	}
}