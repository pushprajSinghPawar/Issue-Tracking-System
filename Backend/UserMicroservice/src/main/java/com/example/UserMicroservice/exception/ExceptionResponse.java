package com.example.UserMicroservice.exception;
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
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ExceptionResponse [message=" + message + ", date=" + date + "]";
	}
	
}
