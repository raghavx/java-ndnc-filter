package com.raghavx.common.filter.service;

import java.io.File;

/**
 * The Interface FilterService.
 */
public interface FilterService {
	
	/**
	 * Filter file.
	 *
	 * @param fileId the file id
	 * @param userId the user id
	 * @param rawFile the raw file
	 * @return the string
	 */
	void filterFile(Long fileId,Long userId,File rawFile);
}
