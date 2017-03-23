package com.raghavx.ndnc.common.service;

/**
 * The Interface LoadFileService.
 */
public interface LoadFileService {
	
	/**
	 * Upload file.
	 *
	 * @param absoluteFilePath the absolute file path
	 * @param targetTable the target table
	 */
	void uploadFile(String absoluteFilePath,String targetTable);
}
