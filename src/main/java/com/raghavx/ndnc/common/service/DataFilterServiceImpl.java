package com.raghavx.ndnc.common.service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.raghavx.common.utils.CommonUtils;

/**
 * The Class DataFilterServiceImpl.
 */
@Service
public class DataFilterServiceImpl implements DataFilterService {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DataFilterServiceImpl.class);


	/** The temp table service. */
	@Autowired
	private TempTableService tempTableService;	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> filterNumber(String absoluteFilePath,String tempTable,String tempColumn,String targetTable,String targetColumn) {

		List<Long> list = new ArrayList<>();
		Long start = System.currentTimeMillis();
		try{
			tempTableService.createTable(tempTable);
			tempTableService.pushData(tempTable, absoluteFilePath);
			list = tempTableService.findAll(tempTable, tempColumn, targetTable, targetColumn);
		}catch(Exception ex){
			logger.error("Error in filtering the file {}"+ex);
			ex.printStackTrace();
		}finally{
			tempTableService.dropTable(tempTable);
		}
		
		logger.info("Time Taken in filtering the file : {}",(System.currentTimeMillis()-start));
		
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> filterNumber(String absoluteFilePath,String tempTable,String tempColumn,String targetTable,String targetColumn,Map<String,String> andClause) {

		List<Long> list = new LinkedList<>();
		Long start = System.currentTimeMillis();
		try{
			tempTableService.createTable(tempTable);
			tempTableService.pushData(tempTable, absoluteFilePath);
			list = tempTableService.findAll(tempTable, tempColumn, targetTable, targetColumn,andClause);
		}catch(Exception ex){
			logger.error("Error in filtering the file {}"+ex);
			ex.printStackTrace();
		}finally{
			tempTableService.dropTable(tempTable);
		}
		
		logger.info("Time Taken in filtering the file with Clause : {}",(System.currentTimeMillis()-start));
		
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Async
	public Future<File> filterDNDNumber(File splitFile,String tempTable) {

		Long start = System.currentTimeMillis();
		File file = null;
		try{
			tempTableService.createTable(tempTable);
			tempTableService.pushData(tempTable, CommonUtils.enrichFilePath(splitFile.getAbsolutePath()));	
			file  = tempTableService.filterDND(tempTable);
		}catch(Exception ex){
			logger.error("Error in filtering the file {}"+ex);
			ex.printStackTrace();
		}finally{
			tempTableService.dropTable(tempTable);
		}
		logger.info("Time Taken in filtering the dnd number from file : {}",(System.currentTimeMillis()-start));
		
		return new AsyncResult<File>(file);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Async
	public Future<File> filterNonDNDNumber(File splitFile,String tempTable) {

		Long start = System.currentTimeMillis();
		File file = null;
		try{
			tempTableService.createTable(tempTable);
			tempTableService.pushData(tempTable, CommonUtils.enrichFilePath(splitFile.getAbsolutePath()));
			file  = tempTableService.filterNonDND(tempTable);
		}catch(Exception ex){
			logger.error("Error in filtering the file {}"+ex);
			ex.printStackTrace();
		}finally{
			tempTableService.dropTable(tempTable);
		}		
		
		logger.info("Time Taken in filtering the non dnd number from file : {}",(System.currentTimeMillis()-start));
		
		return new AsyncResult<File>(file);
	}


}
