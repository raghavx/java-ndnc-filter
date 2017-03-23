package com.raghavx.ndnc.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class LoadFileServiceImpl.
 */
@Service
public class LoadFileServiceImpl implements LoadFileService {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(LoadFileServiceImpl.class);


	/** The temp table service. */
	@Autowired
	private TempTableService tempTableService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void uploadFile(String absoluteFilePath, String targetTable) {
		tempTableService.pushData(targetTable, absoluteFilePath);;
	}

	
	

}
