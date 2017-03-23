package com.raghavx.common.data.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.raghavx.common.data.model.TempFileForCleanUp;
import com.raghavx.common.data.repository.TempFileForCleanUpRepository;
import com.raghavx.common.data.service.TempFileForCleanUpService;
import com.raghavx.common.utils.CommonUtils;

/**
 * The Class TempFileForCleanUpServiceImpl.
 */
@Service
public class TempFileForCleanUpServiceImpl implements TempFileForCleanUpService{	
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(TempFileForCleanUpServiceImpl.class);
	
	/** The temp file for clean up repository. */
	@Autowired
	private TempFileForCleanUpRepository tempFileForCleanUpRepository;	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveTempFile(File file) {
		TempFileForCleanUp entity = buildEntity(file);
		if(Objects.nonNull(entity)){
			tempFileForCleanUpRepository.save(buildEntity(file));	
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveTempFiles(List<File> files) {
		List<TempFileForCleanUp> entities = buildEntities(files);
		if(!CollectionUtils.isEmpty(entities)){
			tempFileForCleanUpRepository.save(entities);
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveTempFiles(File[] files) {	
		List<TempFileForCleanUp> entities = buildEntities(files);
		if(!CollectionUtils.isEmpty(entities)){
			tempFileForCleanUpRepository.save(entities);
		}
	}
	
	/**
	 * Builds the entity.
	 *
	 * @param file the file
	 * @return the temp file for clean up
	 */
	private TempFileForCleanUp buildEntity(File file){
		if(Objects.nonNull(file)){
			TempFileForCleanUp tempFileForCleanUp = new TempFileForCleanUp();
			tempFileForCleanUp.setDatedOn(new Date());
			tempFileForCleanUp.setDeleted(false);
			tempFileForCleanUp.setFilePath(file.getAbsolutePath());
			return tempFileForCleanUp;
		}else{
			return null;
		}
	}
	
	/**
	 * Builds the entities.
	 *
	 * @param files the files
	 * @return the list
	 */
	private List<TempFileForCleanUp> buildEntities(List<File> files){
		List<TempFileForCleanUp> entites = new ArrayList<>();
		files.forEach(file -> {
			TempFileForCleanUp entity = buildEntity(file);
			if(Objects.nonNull(entity))
			entites.add(entity);
		});
		return entites;
	}
	
	/**
	 * Builds the entities.
	 *
	 * @param files the files
	 * @return the list
	 */
	private List<TempFileForCleanUp> buildEntities(File[] files){
		List<TempFileForCleanUp> entites = new ArrayList<>();
		for(int i=0;i<files.length;i++){
			TempFileForCleanUp entity = buildEntity(files[i]);
			if(Objects.nonNull(entity))
			entites.add(entity);
		}
		return entites;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteTempFiles() {
		Date ts = CommonUtils.getDaysBefore(1);
		List<TempFileForCleanUp> files = tempFileForCleanUpRepository.findFilesToBeCleanUp(ts);
		files.forEach(file-> {
			try{
			File f = new File(CommonUtils.enrichFilePath(file.getFilePath()));
			f.delete();
			file.setDeleted(true);
			}catch(Exception e){
				logger.error("Error in deleted the temporary file {} ",file.getId());
			}
		});
		
		tempFileForCleanUpRepository.save(files);
		
	}

	
}
