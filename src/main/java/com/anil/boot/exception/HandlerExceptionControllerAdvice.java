package com.anil.boot.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandlerExceptionControllerAdvice {

	@ExceptionHandler(ResourceNotfoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<ExceptionResponse>  handleResourceNotFound(ResourceNotfoundException exception,
			HttpServletRequest request) {

		List<String> list=new ArrayList<>();
		list.add(exception.getLocalizedMessage());
		//list.add(exception.getCause().getMessage());
		
		ExceptionResponse errorResponse = new ExceptionResponse("Record Not Found",list);
		//errorResponse.setMessageError(exception.getMessage());
		errorResponse.setDetails(list);
		//errorResponse.getDetails(request.getRequestURL());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

	}
	/*
	 * @ExceptionHandler(ResourceNotfoundException.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.NOT_FOUND) public
	 * ResponseEntity<ExceptionResponse>
	 * internalServerError(ResourceNotfoundException exception, HttpServletRequest
	 * request) {
	 * 
	 * List<String> list=new ArrayList<>();
	 * list.add(exception.getLocalizedMessage());
	 * 
	 * ExceptionResponse errorResponse = new
	 * ExceptionResponse("InternalError",list);
	 * //errorResponse.setMessageError(exception.getMessage());
	 * errorResponse.setDetails(list);
	 * //errorResponse.getDetails(request.getRequestURL()); return new
	 * ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 * 
	 * }
	 */
	/*
	 * @ExceptionHandler(Exception.class)
	 * 
	 * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	 * public @ResponseBody ExceptionResponse handleException(Exception exception,
	 * HttpServletRequest request) {
	 * 
	 * ExceptionResponse errorResponse = new ExceptionResponse();
	 * errorResponse.setMessageError(exception.getMessage());
	 * 
	 * return errorResponse;
	 * 
	 * }
	 */
}
