package com.example.AdminMicroservice.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminPreviligeException.
 *
 * @author pushpraj singh pawar
 * @category exception
 */
public class AdminPreviligeException extends Exception{

	/** throws exception when admin does something even it doesnt have the previlige to do. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new admin previlige exception.
	 *
	 * @param msg the msg
	 */
	public AdminPreviligeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}
