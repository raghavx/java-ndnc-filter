package com.raghavx.common.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raghavx.common.data.model.DndDataUpdate;

/**
 * The Interface DndDataUpdateRepository.
 */
@Repository
public interface DndDataUpdateRepository extends JpaRepository<DndDataUpdate, Long> {
	
	@Query("SELECT ddu FROM DndDataUpdate ddu where ddu.id = (select max(dd.id) FROM DndDataUpdate dd)")
	DndDataUpdate getLastDNDDataUpdateStatus();
}
