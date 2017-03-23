package com.raghavx.common.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghavx.common.data.model.TempFileForCleanUp;

/**
 * The Interface TempFileForCleanUpRepository.
 */
@Repository
public interface TempFileForCleanUpRepository extends JpaRepository<TempFileForCleanUp, Long> {

	/**
	 * Find files to be clean up.
	 *
	 * @param date the date
	 * @return the list
	 */
	@Query("SELECT file FROM TempFileForCleanUp file WHERE file.deleted = false AND file.datedOn < :date")
	List<TempFileForCleanUp> findFilesToBeCleanUp(@Param("date") Date date);

}
