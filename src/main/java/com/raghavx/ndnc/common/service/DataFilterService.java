package com.raghavx.ndnc.common.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * The Interface DataFilterService.
 */
public interface DataFilterService {
	
	/**
	 * Filter number.
	 *
	 * @param absoluteFilePath the absolute file path
	 * @param tempTable the temp table
	 * @param tempColumn the temp column
	 * @param targetTable the target table
	 * @param targetColumn the target column
	 * @return the list
	 */
	List<Long> filterNumber(String absoluteFilePath,String tempTable,String tempColumn,String targetTable,String targetColumn);

	/**
	 * Filter number.
	 *
	 * @param absoluteFilePath the absolute file path
	 * @param tempTable the temp table
	 * @param tempColumn the temp column
	 * @param targetTable the target table
	 * @param targetColumn the target column
	 * @param andClause the and clause
	 * @return the list
	 */
	List<Long> filterNumber(String absoluteFilePath, String tempTable, String tempColumn, String targetTable,
			String targetColumn, Map<String, String> andClause);


	/**
	 * Filter DND number.
	 *
	 * @param splitFile the split file
	 * @param tempTable the temp table
	 * @return the file
	 */
	Future<File> filterDNDNumber(File splitFile,String tempTable);

	/**
	 * Filter non DND number.
	 *
	 * @param splitFile the split file
	 * @param tempTable the temp table
	 * @return the file
	 */
	Future<File> filterNonDNDNumber(File splitFile, String tempTable);
}
