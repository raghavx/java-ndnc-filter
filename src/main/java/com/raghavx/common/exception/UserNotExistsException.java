package com.raghavx.common.exception;

/**
 * The Class UserNotExistsException.
 */
public class UserNotExistsException extends RestApiException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new user not exists exception.
	 *
	 * @param message the message
	 * @param error the error
	 */
	public UserNotExistsException(String message,String error){		
		super(message,error);
	}

}
