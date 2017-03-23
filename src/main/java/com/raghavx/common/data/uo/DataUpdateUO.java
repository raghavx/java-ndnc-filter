package com.raghavx.common.data.uo;

import java.util.Date;

import lombok.Data;

/**
 * Instantiates a new data update UO.
 */
@Data
public class DataUpdateUO {	
	
	/** The total records. */
	private long totalRecords;
	
	/** The inserted records. */
	private long insertedRecords;
	
	/** The updated records. */
	private long updatedRecords;
	
	/** The update on. */
	private Date updateOn;
	
}
