package com.crud.crudoperations11.Exceptions;

import com.crud.crudoperations11.Exceptions.BadRequestException;
import com.crud.crudoperations11.Exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionalHandler 
{
	@ExceptionHandler(BadRequestException.class)
	
		public ResponseEntity<ErrorResponses>handleBadRequestException(BadRequestException ex,HttpServletRequest request)
		{
			ErrorResponses errorresponses=new ErrorResponses(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),request.getRequestURI());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorresponses);
		}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<ErrorResponses>ResourceNotFoundException(ResourceNotFoundException ex,HttpServletRequest request)
	{
		ErrorResponses errorresponses=new ErrorResponses(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorresponses);
	}
	

}
