package com.raghavx.ndnc.data.service.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.raghavx.common.data.model.DndDataUpdate;
import com.raghavx.common.data.repository.DndDataUpdateRepository;
import com.raghavx.common.data.service.DataUpdateService;
import com.raghavx.common.data.uo.DataUpdateUO;
import com.raghavx.ndnc.common.service.TempTableService;

/**
 * The Class DndDataUpdateServiceImpl.
 */
@Service
public class DndDataUpdateServiceImpl implements DataUpdateService{
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DndDataUpdateServiceImpl.class);


	/** The dnd data update repository. */
	@Autowired
	private DndDataUpdateRepository dndDataUpdateRepository;
	
	/** The conversion service. */
	@Autowired
	private ConversionService conversionService;
	
	/** The temp table service. */
	@Autowired
	private TempTableService tempTableService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUpdateUO getLastDataUpdateStatus() {

		DndDataUpdate dndDataUpdate = dndDataUpdateRepository.getLastDNDDataUpdateStatus();
		return conversionService.convert(dndDataUpdate, DataUpdateUO.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void refreshData(MultipartFile file) {


		File rawFile = null;
		try {
			rawFile = File.createTempFile("upload", "raw");
			file.transferTo(rawFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tempTableService.pushData("dnd_data", rawFile.getAbsolutePath());
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean dataUpdate(String basePath, String name , int numberOfFiles) {
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(basePath).append(name).append("_");
		String file = null;
		long start = 0;
		for(int i=0;i<numberOfFiles;i++){
			file = fileNameBuilder.toString()+String.valueOf(i)+".csv";
			start = System.currentTimeMillis();
			tempTableService.pushData("dnd_data", file);
			logger.info("Dnd Upadte for file {} completed in {} ",file,(System.currentTimeMillis()-start));
			
		}
		
		return true;
	}
	
}
