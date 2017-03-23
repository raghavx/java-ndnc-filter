package com.raghavx.common.constant;


/**
 * The Enum TransactionType.
 */
public enum TransactionType {
	
	/** The credit. */
	CREDIT("credit"),
	
	/** The debit. */
	DEBIT("debit");
	
	/** The type. */
	private String type;
	
	
	/**
	 * Instantiates a new transaction type.
	 *
	 * @param type the type
	 */
	private TransactionType(String type) {
		this.type = type;
	}
	
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType(){
		return type;
	}



}
