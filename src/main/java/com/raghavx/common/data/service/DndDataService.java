package com.raghavx.common.data.service;

import com.raghavx.common.data.model.DndData;

/**
 * The Interface DndDataService.
 */
public interface DndDataService {

	/**
	 * Save.
	 *
	 * @param dndData the DndData
	 * @return the dnd data
	 */
	DndData save(DndData dndData);

	/**
	 * Find by id.
	 *
	 * @param dndDataId the dndData id
	 * @return the dnd data
	 */
	DndData findById(Long dndDataId);
	
}
