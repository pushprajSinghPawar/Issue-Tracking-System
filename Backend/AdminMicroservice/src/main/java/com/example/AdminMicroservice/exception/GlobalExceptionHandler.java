package com.example.AdminMicroservice.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler.
 *
 * @author pushpraj singh pawar class to handle the exception occuring in the
 *         Admin - microservice
 * @category exception
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Gets the response.
	 *
	 * @param exception the exception
	 * @return the response
	 * @category exception method to handle the exception when data entered in the
	 *           API request does'nt match the requirement validation of the model
	 *           classes
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> getresponse(MethodArgumentNotValidException exception) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				exception.getBindingResult().getFieldError().getDefaultMessage(), new Date());
		logger.error(exceptionResponse.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}

	/**
	 * Gets the admin previlige exception.
	 *
	 * @param ex the ex
	 * @return the admin previlige exception
	 * @category exception handle exception when admin does something even it doesnt
	 *           have the previlige to do
	 */
	@ExceptionHandler(AdminPreviligeException.class)
	public ResponseEntity<ExceptionResponse> getAdminPreviligeException(AdminPreviligeException ex) {
		logger.error(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage(), new Date()));
	}

	/**
	 * All exceptions.
	 *
	 * @param ex the ex
	 * @return the response entity
	 * @category exception
	 * handle all the exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> allExceptions(Exception ex) {
		logger.error(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(ex.getMessage(), new Date()));
	}

}
