package com.raghavx.common.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghavx.common.data.model.DndData;

/**
 * The Interface DndDataRepository.
 */
@Repository
public interface DndDataRepository extends JpaRepository<DndData, Long> {

	/**
	 * Find by phone numbers.
	 *
	 * @param phoneNumbers the phone numbers
	 * @return the list
	 */
	@Query("SELECT dnd FROM DndData dnd WHERE dnd.phoneNumber in (:phoneNumbers)")
	List<DndData> findByPhoneNumbers(@Param("phoneNumbers") List<Long> phoneNumbers);
}
