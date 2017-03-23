package com.raghavx.common.exception;

/**
 * The Class RestApiException.
 */
public class RestApiException extends RuntimeException{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The error. */
	private String error;
	
	/** The message. */
	private String message;
	
	/**
	 * Instantiates a new payment failure exception.
	 *
	 * @param message the message
	 * @param error the error
	 */
	public RestApiException(String message,String error){
		this.message = message;
		this.error =error;
	}
	
	public RestApiException(String message){
		this.message = message;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getLocalizedMessage(){
		return message;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getMessage(){
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
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}
	

}
