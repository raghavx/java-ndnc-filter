package com.raghavx.common.data.uo;

import lombok.Data;

/**
 * Instantiates a new NDNC setting UO.
 */
@Data
public class ConfigSettingUO {	
	
	/** The max upload files. */
	private long maxUploadFiles;
	
	/** The max upload records. */
	private long maxUploadRecords;
	
	/** The max file size. */
	private long maxFileSize;
}
