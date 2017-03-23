package com.raghavx.common.data.uo;

import java.util.Date;

import lombok.Data;


/**
 * Instantiates a new file UO.
 */
@Data
public class FileUO {
	
	/** The file id. */
	private long fileId;
	
	/** The user name. */
	private String userName;
	
	/** The total numbers. */
	private long totalNumbers;
	
	/** The dnd numbers. */
	private long dndNumbers;
	
	/** The non dnd numbers. */
	private long nonDndNumbers;
	
	/** The raw file path. */
	private String rawFilePath;
	
	/** The filtered dnd file path. */
	private String filteredDndFilePath;
	
	/** The filtered non dnd file path. */
	private String filteredNonDndFilePath;
	
    /** The status. */
    private String status;
	
	/** The uploaded on. */
	private Date uploadedOn;
	
	/** The time taken. */
	private double timeTaken;
		

}
