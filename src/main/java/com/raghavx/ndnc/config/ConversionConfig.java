package com.raghavx.ndnc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.raghavx.common.data.converter.DndDataUpdateToDndDataUpdateUOConverter;
import com.raghavx.common.data.converter.FileToFileUOConverter;

/**
 * The Class ConversionConfig.
 */
@Configuration
public class ConversionConfig {

	 /**
 	 * Gets the conversion service.
 	 *
 	 * @return the conversion service
 	 */
 	@Bean(name="conversionService")
	    public ConversionService getConversionService() {
		 
		 DefaultConversionService conversionService = new DefaultConversionService();
		 
		 conversionService.addConverter(new FileToFileUOConverter());
		 conversionService.addConverter(new DndDataUpdateToDndDataUpdateUOConverter());
		 return conversionService;
	    }
}
