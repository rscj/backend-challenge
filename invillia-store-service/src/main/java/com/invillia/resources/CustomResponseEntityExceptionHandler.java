package com.invillia.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.invillia.exceptions.DuplicatedNameException;
import com.invillia.exceptions.RequiredFieldException;
import com.invillia.exceptions.EntityNotFoundException;
import com.invillia.exceptions.InvalidRequestException;
import com.invillia.models.ErrorMessage;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final String DOCUMENTATION_URL_BASE = "http://invilia.com/store/service/errors/list";
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(ex.getMessage(), DOCUMENTATION_URL_BASE);
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RequiredFieldException.class) 
	public final ResponseEntity<ErrorMessage> handleNameRequiredException(RequiredFieldException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage("Field required exception: " + ex.getMessage(), DOCUMENTATION_URL_BASE + "/#field_required");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public final ResponseEntity<ErrorMessage> handleDuplicatedNameException(InvalidRequestException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage("Invalid request exception: " + ex.getMessage(), DOCUMENTATION_URL_BASE + "/#invalid_request");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicatedNameException.class)
	public final ResponseEntity<ErrorMessage> handleDuplicatedNameException(DuplicatedNameException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage("Duplicated name exception: " + ex.getMessage(), DOCUMENTATION_URL_BASE + "/#duplicated_name");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class) 
	public final ResponseEntity<ErrorMessage> handleStoreNotFoundException(EntityNotFoundException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage("Entity found exception: " + ex.getMessage(), DOCUMENTATION_URL_BASE + "/#entity_not_found");
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
	}
}