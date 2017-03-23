package com.raghavx.ndnc.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghavx.common.data.service.FileService;
import com.raghavx.common.data.uo.FileUO;
import com.raghavx.common.data.uo.SearchUO;
import com.raghavx.common.exception.UserNotExistsException;
import com.raghavx.ndnc.auth.model.User;
import com.raghavx.ndnc.auth.service.UserService;

/**
 * The Class SearchController.
 */
@RestController
public class SearchController {
	
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/** The file service. */
	@Autowired
	private FileService fileService;

	
	/**
	 * Search after date.
	 *
	 * @param uid the uid
	 * @param after the after
	 * @param pageable the pageable
	 * @return the search UO
	 */
	@GetMapping("/processedfile/user/{uid}/afterdate/{after}")
	public @ResponseBody SearchUO searchAfterDate(
			@PathVariable("uid") long uid, @PathVariable("after") Date after, Pageable pageable) {

		User user = userService.findById(uid);
		
		if (Objects.isNull(user)) {
			throw new UserNotExistsException("User not Exists", "User not Exists" + uid);
		}

		List<FileUO> files =  fileService.listOfFileProcessedAfterDateForUser(uid,after, pageable);		
		return getSearchUO(files);
	}

	/**
	 * Search between date.
	 *
	 * @param uid the uid
	 * @param start the start
	 * @param end the end
	 * @param pageable the pageable
	 * @return the search UO
	 */
	@GetMapping("/processedfile/user/{uid}/startdate/{start}/enddate/{end}")
	public @ResponseBody SearchUO searchBetweenDate(
			@PathVariable("uid") long uid, @PathVariable("start") Date start, @PathVariable("end") Date end, Pageable pageable) {

		User user = userService.findById(uid);
		
		if (Objects.isNull(user)) {
			throw new UserNotExistsException("User not Exists", "User not Exists" + uid);
		}
		
		List<FileUO> files = fileService.listOfFileProcessedBetweenDateForUser(uid,start,end, pageable);
		return getSearchUO(files);
	}
	

	
	/**
	 * Gets the search UO.
	 *
	 * @param files the files
	 * @return the search UO
	 */
	private SearchUO getSearchUO(List<FileUO> files) {
		SearchUO searchUO = new SearchUO();
		searchUO.setTotalNumberOfFiles(files.size());
		long dndRecords = 0;
		long nonDndRecords = 0;
		long totalRecords =0;
		for(FileUO file : files){
			dndRecords += file.getDndNumbers();
			nonDndRecords += file.getNonDndNumbers();
			totalRecords += file.getTotalNumbers();
		}
		searchUO.setDndRecords(dndRecords);		
		searchUO.setNonDndRecords(nonDndRecords);		
		searchUO.setTotalRecords(totalRecords);
		return searchUO;
	}
}
