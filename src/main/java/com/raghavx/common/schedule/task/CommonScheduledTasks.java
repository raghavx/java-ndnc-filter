package com.raghavx.common.schedule.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.raghavx.common.data.service.TempFileForCleanUpService;

/**
 * The Class CommonScheduledTasks.
 */
@Component
public class CommonScheduledTasks {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CommonScheduledTasks.class);
	
	/** The temp file for clean up service. */
	@Autowired
	private TempFileForCleanUpService tempFileForCleanUpService;

	/** The Constant dateFormat. */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	
	/**
	 * Invalidate expired reward.
	 */
	@Scheduled(cron="${sch.ndnc.temp.file.cleanup.cron}")
	public void invalidateExpiredReward() {

		logger.info("InvalidateExpiredReward Job ran at {}", dateFormat.format(new Date()));
		
		tempFileForCleanUpService.deleteTempFiles();

	}


}
