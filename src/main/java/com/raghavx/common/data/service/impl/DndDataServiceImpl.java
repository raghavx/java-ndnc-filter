package com.raghavx.common.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavx.common.data.model.DndData;
import com.raghavx.common.data.repository.DndDataRepository;
import com.raghavx.common.data.service.DndDataService;

/**
 * The Class DndDataServiceImpl.
 */
@Service
public class DndDataServiceImpl implements DndDataService{

	@Autowired
	private DndDataRepository dndDataRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DndData save(DndData dndData) {
		return dndDataRepository.save(dndData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DndData findById(Long dndDataId) {
		return dndDataRepository.getOne(dndDataId);
	}

	
}
