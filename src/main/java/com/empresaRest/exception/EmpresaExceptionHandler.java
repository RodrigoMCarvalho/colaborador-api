package com.empresaRest.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmpresaExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	  public ResponseEntity<?>  errorCPFDuplicado() {
		return new ResponseEntity<>("CPF informado já está cadastrado na empresa.", HttpStatus.BAD_REQUEST);
	  }
}
