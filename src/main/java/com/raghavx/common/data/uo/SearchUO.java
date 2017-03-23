package com.raghavx.common.data.uo;

import java.util.List;

import lombok.Data;

/**
 * Instantiates a new search UO.
 */
@Data
public class SearchUO {	
	
	/** The files. */
	private List<FileUO> files;
	
	/** The total number of files. */
	private long totalNumberOfFiles;
	
	/** The total records. */
	private long totalRecords;
	
	/** The dnd records. */
	private long dndRecords;
	
	/** The non dnd records. */
	private long nonDndRecords;
}
