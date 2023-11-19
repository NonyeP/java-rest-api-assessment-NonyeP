package com.cbfacademy.apiassessment.exceptionHandlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/*
 * This class customizes the response in the case of an all exceptions.
 * It defines the errors by creating the features you will like to personalise
 * It gives https status of HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.NOT_FOUND,
 * and HttpStatus.BAD_REQUEST
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetailsInProject> handleAllException(Exception ex,WebRequest request){
		
		ErrorDetailsInProject errorDetails = new  ErrorDetailsInProject(LocalDateTime.now(),ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetailsInProject>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetailsInProject> handleUserNotFoundException(Exception ex,WebRequest request){
		
		ErrorDetailsInProject errorDetails = new  ErrorDetailsInProject(LocalDateTime.now(),ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetailsInProject>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			 HttpHeaders headers,
			 HttpStatusCode status,
			 WebRequest request){
		ErrorDetailsInProject errorDetails = new  ErrorDetailsInProject(LocalDateTime.now(),"Total Errors: " + ex.getErrorCount() + 
				" The first one is  : " + ex.getMessage(),
				request.getDescription(false));
		//ex.getFieldError().getDefaultMessage();
			return new ResponseEntity(errorDetails,HttpStatus.BAD_REQUEST);
	}


}
