package com.raghavx.common.data.service;

import org.springframework.web.multipart.MultipartFile;

import com.raghavx.common.data.uo.DataUpdateUO;

/**
 * The Interface DataUpdateService.
 */
public interface DataUpdateService {

	/**
	 * Gets the last data update status.
	 *
	 * @return the last data update status
	 */
	DataUpdateUO getLastDataUpdateStatus();

	/**
	 * Refresh data.
	 *
	 * @param file the file
	 */
	void refreshData(MultipartFile file);

	/**
	 * data update.
	 *
	 * @param basePath the base path
	 * @param name the name
	 * @param numberOfFiles the number of files
	 * @return true, if successful
	 */
	boolean dataUpdate(String basePath,String name,int numberOfFiles);

	
}
