package com.myhome.full_project.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myhome.full_project.services.exceptions.DataIntegrityException;
import com.myhome.full_project.services.exceptions.IdIsNotfound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(IdIsNotfound.class)
	public ResponseEntity<StandardError> idIsNotfound(IdIsNotfound e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError("Erro de validação");
		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError1> dataIntegrityException(DataIntegrityException e){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError1 err = new StandardError1(Instant.now(), status.value(), e.getMessage());
		return ResponseEntity.status(status).body(err);
	}
	
	
	
}
