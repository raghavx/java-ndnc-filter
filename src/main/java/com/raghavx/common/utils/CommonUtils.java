package com.raghavx.common.utils;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CommonUtils.
 */
public class CommonUtils {
	
	/** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    	
	/**
	 * Gets the days after.
	 *
	 * @param after the after
	 * @return the days after
	 */
	public static Date getDaysAfter(int after) {
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.DATE, after);
		return c.getTime();
	}
	
	/**
	 * Gets the days before.
	 *
	 * @param before the before
	 * @return the days before
	 */
	public static Date getDaysBefore(int before) {
		Calendar c = Calendar.getInstance();    
		c.add(Calendar.DATE, -before);
		return c.getTime();
	}
	
	
	public static double getDifferenceBetween(Date d1,Date d2){
		return (d1.getTime()-d2.getTime())/1000;
	}
	

	/**
	 * Gets the date after.
	 *
	 * @param dob the dob
	 * @param days the days
	 * @return the date after
	 */
	public static Date getDateAfter(Date dob,int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dob);
		cal.add(Calendar.DATE, days);
		Date beforeDob = cal.getTime();
		return beforeDob;
	}
	
	public static String enrichFilePath(String path){
		return path.replace("\\", "/");
	}
	
}
