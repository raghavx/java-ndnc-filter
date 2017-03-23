package com.raghavx.common.exception;

/**
 * The Class InSufficientBalanceException.
 */
public class InSufficientBalanceException extends RestApiException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The records. */
	private long records;
	
	/**
	 * Instantiates a new in sufficient balance exception.
	 *
	 * @param message the message
	 * @param error the error
	 * @param records the records
	 */
	public InSufficientBalanceException(String message,String error,long records){		
		super(message,error);
		this.records = records;
	}

	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	public long getRecords() {
		return records;
	}
}
