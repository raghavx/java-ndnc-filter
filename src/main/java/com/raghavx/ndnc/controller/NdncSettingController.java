package com.raghavx.ndnc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghavx.common.data.uo.ConfigSettingUO;

/**
 * The Class NDNCSettingController.
 */
@RestController
public class NdncSettingController {
	
	/** The max upload files. */
	@Value("${ndnc.max.upload.files}")
	private long maxUploadFiles;
	
	/** The max upload records. */
	@Value("${ndnc.max.upload.records}")
	private long maxUploadRecords;
	
	/** The max file size. */
	@Value("${ndnc.max.file.size}")
	private long maxFileSize;
	
	
	
	/**
	 * Gets the NDNC setting.
	 *
	 * @return the NDNC setting
	 */
	@GetMapping("/settings")
	public @ResponseBody ConfigSettingUO getNDNCSetting() {
		
		ConfigSettingUO nDNCSettingUO = new ConfigSettingUO();
		nDNCSettingUO.setMaxFileSize(maxFileSize);
		nDNCSettingUO.setMaxUploadFiles(maxUploadFiles);
		nDNCSettingUO.setMaxUploadRecords(maxUploadRecords);
		
		return nDNCSettingUO;
	}

}
