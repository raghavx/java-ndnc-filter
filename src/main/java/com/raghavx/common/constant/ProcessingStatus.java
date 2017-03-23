package com.raghavx.common.constant;

/**
 * The Enum ProcessingStatus.
 */
public enum ProcessingStatus {
	
	/** The in progress. */
	IN_PROGRESS("in_progress"),
	
	/** The completed. */
	COMPLETED("completed"), 
	
	/** The upload failed. */
	UPLOAD_FAILED("upload_failed"),
	
	/** The insufficient balance. */
	INSUFFICIENT_BALANCE("insufficient_balance"),
	
	/** The upload failed. */
	PROCESSED_FILE_UPLOAD_FAILED("processed_file_upload_failed");
	
	/** The status. */
	private String status;
	
	
	/**
	 * Instantiates a new processing status.
	 *
	 * @param status the status
	 */
	private ProcessingStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getstatus(){
		return status;
	}



}
