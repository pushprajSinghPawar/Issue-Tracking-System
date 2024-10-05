package com.security.exception;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionResponse.
 */
public class ExceptionResponse {
	
	/** The message. */
	String message;
	
	/** The date. */
	Date date;
	
	/**
	 * Instantiates a new exception response.
	 *
	 * @param message the message
	 * @param date the date
	 */
	public ExceptionResponse(String message, Date date) {
		super();
		this.message = message;
		this.date = date;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}	
}
