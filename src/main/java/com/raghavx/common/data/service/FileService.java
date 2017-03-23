package com.raghavx.common.data.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.raghavx.common.data.model.UserFile;
import com.raghavx.common.data.uo.FileUO;
import com.raghavx.ndnc.auth.model.User;

/**
 * The Interface FileService.
 */
public interface FileService {

	/**
	 * List of file processed after date for user.
	 *
	 * @param uid the uid
	 * @param after the after
	 * @param pageable the pageable
	 * @return the list
	 */
	List<FileUO> listOfFileProcessedAfterDateForUser(long uid, Date after, Pageable pageable);

	/**
	 * List of file processed between date for user.
	 *
	 * @param uid the uid
	 * @param start the start
	 * @param end the end
	 * @param pageable the pageable
	 * @return the list
	 */
	List<FileUO> listOfFileProcessedBetweenDateForUser(long uid, Date start, Date end, Pageable pageable);

	/**
	 * Save.
	 *
	 * @param file the file
	 * @return the file
	 */
	UserFile save(UserFile file);

	/**
	 * Find by id.
	 *
	 * @param fileId the file id
	 * @return the file
	 */
	UserFile findById(Long fileId);

	List<UserFile> findByUserOrderByUploadedOnDesc(User user);
	
}
