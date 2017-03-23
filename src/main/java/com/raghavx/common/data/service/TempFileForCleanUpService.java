package com.raghavx.common.data.service;

import java.io.File;
import java.util.List;

/**
 * The Interface TempFileForCleanUpService.
 */
public interface TempFileForCleanUpService {

	/**
	 * Save temp file.
	 *
	 * @param file the file
	 */
	void saveTempFile(File file);

	/**
	 * Save temp files.
	 *
	 * @param files the files
	 */
	void saveTempFiles(List<File> files);

	/**
	 * Save temp files.
	 *
	 * @param files the files
	 */
	void saveTempFiles(File[] files);
	
	/**
	 * Delete temp files.
	 */
	void deleteTempFiles();

	
}
