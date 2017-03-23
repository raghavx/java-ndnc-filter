package com.raghavx.common.data.converter;

import java.util.Objects;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.raghavx.common.data.model.UserFile;
import com.raghavx.common.data.uo.FileUO;
import com.raghavx.common.utils.CommonUtils;

/**
 * The Class FileToFileUOConverter.
 */
@Component
public class FileToFileUOConverter implements Converter<UserFile, FileUO>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileUO convert(UserFile file) {
		FileUO fileUO = null;
		if(Objects.nonNull(file)){
			fileUO = new FileUO();
			fileUO.setDndNumbers(file.getDndNumbers());
			fileUO.setFileId(file.getFileId());
			fileUO.setFilteredDndFilePath(file.getDndFilteredFilePath());
			fileUO.setFilteredNonDndFilePath(file.getNonDndFilteredFilePath());
			fileUO.setNonDndNumbers(file.getNonDndNumbers());
			fileUO.setStatus(file.getStatus().getstatus());
			fileUO.setTimeTaken(CommonUtils.getDifferenceBetween(file.getUpdatedOn(), file.getUploadedOn()));
			fileUO.setUserName(file.getUser().getUsername());
			fileUO.setUploadedOn(file.getUploadedOn());
		}
		return fileUO;
	}

}
