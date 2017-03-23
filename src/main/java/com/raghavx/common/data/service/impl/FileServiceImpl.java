package com.raghavx.common.data.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raghavx.common.data.model.UserFile;
import com.raghavx.common.data.repository.FileRepository;
import com.raghavx.common.data.service.FileService;
import com.raghavx.common.data.uo.FileUO;
import com.raghavx.common.utils.CommonUtils;
import com.raghavx.ndnc.auth.model.User;
import com.raghavx.ndnc.auth.service.UserService;

/**
 * The Class FileServiceImpl.
 */
@Service
public class FileServiceImpl implements FileService{
	
	/** The file repository. */
	@Autowired
	private FileRepository fileRepository;
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The conversion service. */
	@Autowired
	private ConversionService conversionService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FileUO> listOfFileProcessedAfterDateForUser(long uid, Date date, Pageable pageable) {
		
		

		
		if(Objects.isNull(date)){
			date = CommonUtils.getDaysBefore(1);
		}
		 Page<UserFile> filePages = fileRepository.listOfFileProcessedAfterDateForUser(date, userService.findById(uid), pageable);
		 
		 List<FileUO> files = new LinkedList<>();
		 
		 filePages.getContent().forEach(file -> files.add(conversionService.convert(file, FileUO.class)));
		 
		 return files;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FileUO> listOfFileProcessedBetweenDateForUser(long uid, Date start, Date end, Pageable pageable) {
		 Page<UserFile> filePages = fileRepository.listOfFileProcessedBetweenDatesForUser(userService.findById(uid),start,end, pageable);
		 
		 List<FileUO> files = new LinkedList<>();
		 
		 filePages.getContent().forEach(file -> files.add(conversionService.convert(file, FileUO.class)));
		 
		 return files;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserFile save(UserFile file) {
		return fileRepository.save(file);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserFile findById(Long fileId) {
		return fileRepository.findOne(fileId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserFile> findByUserOrderByUploadedOnDesc(User user){
		return fileRepository.findByUserOrderByUploadedOnDesc( user);
	}

	
}
