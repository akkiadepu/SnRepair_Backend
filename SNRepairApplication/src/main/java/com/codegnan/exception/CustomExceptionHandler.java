package com.codegnan.exception;

import java.util.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class CustomExceptionHandler {
	
//	@ExceptionHandler(InvalidAdminIdException.class)
//	public ResponseEntity<ErrorResponse> meth1(InvalidAdminIdException e){
//		HttpStatus status = HttpStatus.NOT_FOUND;
//		String message = e.getMessage();
//		ErrorResponse errorResponse = new ErrorResponse(status, message);
//		return new ResponseEntity<ErrorResponse>(errorResponse, status);
//	}
//	
//	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<ErrorResponse> meth2(Exception e){
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//		String message = e.getMessage();
//		ErrorResponse errorResponse = new ErrorResponse(status, message);
//		return new ResponseEntity<ErrorResponse>(errorResponse, status);
//	}
//
//
//}
	
	
	
	@ExceptionHandler(InvalidAdminIdException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAdminIdException(InvalidAdminIdException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        List<String> stackTrace = Arrays.stream(e.getStackTrace())
                                         .map(StackTraceElement::toString)
                                         .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse(status, message, stackTrace);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = e.getMessage();
        List<String> stackTrace = Arrays.stream(e.getStackTrace())
                                         .map(StackTraceElement::toString)
                                         .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse(status, message, stackTrace);
        return new ResponseEntity<>(errorResponse, status);
    }
}
