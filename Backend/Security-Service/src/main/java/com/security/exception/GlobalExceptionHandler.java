package com.security.exception;
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
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> getresponse(MethodArgumentNotValidException exception){
		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getBindingResult().getFieldError().getDefaultMessage(), new Date());
		logger.error(exceptionResponse.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
	}
	
	/**
	 * Gets the response 2.
	 *
	 * @param us the us
	 * @return the response 2
	 */
	@ExceptionHandler(UserNotAddedException.class)
	public ResponseEntity<ExceptionResponse> getresponse2(UserNotAddedException us){
		ExceptionResponse exceptionResponse = new ExceptionResponse(us.getMessage(), new Date());
		logger.error(exceptionResponse.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
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
