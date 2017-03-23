package com.raghavx.ndnc.common.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The Interface TempTableService.
 */
public interface TempTableService {
	
	/**
	 * Creates the table.
	 *
	 * @param tempTable the temp table
	 */
	void createTable(String tempTable);
	
	/**
	 * Drop table.
	 *
	 * @param tempTable the temp table
	 */
	void dropTable(String tempTable);
	
	/**
	 * Push data.
	 *
	 * @param tempTable the temp table
	 * @param filePath the file path
	 */
	void pushData(String tempTable, String filePath);	
	
	/**
	 * Find all.
	 *
	 * @param tempTable the temp table
	 * @param tempColumn the temp column
	 * @param targetTable the target table
	 * @param targetColumn the target column
	 * @return the list
	 */
	public List<Long> findAll(String tempTable,String tempColumn,String targetTable,String targetColumn);
	
	/**
	 * Find all.
	 *
	 * @param tempTable the temp table
	 * @param tempColumn the temp column
	 * @param targetTable the target table
	 * @param targetColumn the target column
	 * @param andClause the and clause
	 * @return the list
	 */
	public List<Long> findAll(String tempTable,String tempColumn,String targetTable,String targetColumn , Map<String,String> andClause);

	/**
	 * Filter DND.
	 *
	 * @param tempTable the temp table
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File filterDND(String tempTable) throws IOException;

	/**
	 * Filter non DND.
	 *
	 * @param tempTable the temp table
	 * @return the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public File filterNonDND(String tempTable) throws IOException;
	
}
                        