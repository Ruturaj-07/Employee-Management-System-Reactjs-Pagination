package com.usecase.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.usecase.model.ResponseError;

@ControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleGlobalException(ApiException apiException,WebRequest request){
		ErrorDeatils errorDeatils=new ErrorDeatils(new Date(), apiException.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDeatils, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleGlobalException(ResourceNotFoundException resourceNotFoundException,WebRequest request){
		ErrorDeatils errorDeatils=new ErrorDeatils(new Date(), resourceNotFoundException.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDeatils, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest request){
		ErrorDeatils errorDeatils=new ErrorDeatils(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDeatils, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseError handleCustomeException(ConstraintViolationException ex) {
		ResponseError responseError = new ResponseError();
		List<String> errorMessages = new ArrayList();
		for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
			errorMessages.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
		}
		responseError.setErrorMessage(errorMessages);
		responseError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return responseError;
	}

}
