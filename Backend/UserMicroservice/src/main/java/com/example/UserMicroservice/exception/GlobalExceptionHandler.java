package com.example.UserMicroservice.exception;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.ws.rs.NotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * Expert not found.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(ExpertNotFound.class)
	public ResponseEntity<ExceptionResponse> expertNotFound(ExpertNotFound exception){
		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), new Date());
		logger.error(exceptionResponse.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}
	
	/**
	 * Gets the response.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> getresponse(MethodArgumentNotValidException exception){
		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getBindingResult().getFieldError().getDefaultMessage(), new Date());
		logger.error(exceptionResponse.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}
	
	/**
	 * Handle service unavailable exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(NotFoundException ex) {
        ExceptionResponse errorResponse = new ExceptionResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
	
    /**
     * All exceptions.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> allExceptions(Exception ex) {
    	logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage(), new Date()));
    }

}
