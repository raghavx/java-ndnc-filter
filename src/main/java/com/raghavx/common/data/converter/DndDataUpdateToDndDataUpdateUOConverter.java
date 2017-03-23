package com.raghavx.common.data.converter;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.raghavx.common.data.model.DndDataUpdate;
import com.raghavx.common.data.uo.DataUpdateUO;

/**
 * The Class FileToFileUOConverter.
 */
@Component
public class DndDataUpdateToDndDataUpdateUOConverter implements Converter<DndDataUpdate, DataUpdateUO>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUpdateUO convert(DndDataUpdate dndDataUpdate) {
		DataUpdateUO dndDataUpdateUO = null;
		if(Objects.nonNull(dndDataUpdate)){
			dndDataUpdateUO = new DataUpdateUO();
			dndDataUpdateUO.setInsertedRecords(dndDataUpdate.getInsertedRecords());
			dndDataUpdateUO.setTotalRecords(dndDataUpdate.getTotalRecords());
			dndDataUpdateUO.setUpdatedRecords(dndDataUpdate.getUpdatedRecords());
			dndDataUpdateUO.setUpdateOn(dndDataUpdate.getUpdatedOn());
		}
		return dndDataUpdateUO;
	}

}
