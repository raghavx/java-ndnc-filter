package com.raghavx.common.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghavx.common.data.model.UserFile;
import com.raghavx.ndnc.auth.model.User;

/**
 * The Interface FileRepository.
 */
@Repository
public interface FileRepository extends PagingAndSortingRepository<UserFile,Long> {
		
	/**
	 * Find by user order by uploaded on desc.
	 *
	 * @param user the user
	 * @return the list
	 */
	List<UserFile> findByUserOrderByUploadedOnDesc(User user);	
	
	/**
	 * List of file processed after date for user.
	 *
	 * @param date the date
	 * @param user the user
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT file FROM UserFile file WHERE file.user = :user AND file.uploadedOn > :date order by file.uploadedOn desc")
	Page<UserFile> listOfFileProcessedAfterDateForUser(@Param("date") Date date,@Param("user") User user, Pageable pageable);
	
	/**
	 * List of file processed between dates for user.
	 *
	 * @param user the user
	 * @param date1 the date 1
	 * @param date2 the date 2
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("SELECT file FROM UserFile file WHERE file.user = :user AND file.uploadedOn between :date1 and :date2 order by file.uploadedOn desc")
	Page<UserFile> listOfFileProcessedBetweenDatesForUser(@Param("user") User user,@Param("date1") Date date1,@Param("date2") Date date2, Pageable pageable);

	
}
