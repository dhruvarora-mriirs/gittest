package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	  @ExceptionHandler(CustomException.class)
	  public final ResponseEntity<Object> handleAllExceptions(CustomException ex) {
		  ErrorCode errorCode = ex.getCode();
	    ErrorDTO exceptionResponse =
	        new ErrorDTO(errorCode.getValue(),errorCode.toString());
	    
	    return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	  }
	}


