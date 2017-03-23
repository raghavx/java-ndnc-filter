package com.raghavx.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * The Class DataFormaterUtil.
 */
public class DataFormaterUtil {

	/** The decimal pattern. */
	private static String decimalPattern = "#0.00";

	/** The decimal format. */
	private static DecimalFormat decimalFormat = new DecimalFormat(decimalPattern);

	/** The date pattern. */
	private static String datePattern = "MMM dd, yyyy HH:mm:ss";

	/** The simple date format. */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
	
	/** The dob pattern. */
	private static String dobPattern = "yyyy-dd-MM";

	/** The simple dob format. */
	private static SimpleDateFormat simpleDOBFormat = new SimpleDateFormat(dobPattern);
	
	/** The dd M myyyy. */
	private static String ddMMyyyy = "ddMMyyyy";
	
	/** The dd M myyyy format. */
	private static SimpleDateFormat ddMMyyyyFormat = new SimpleDateFormat(ddMMyyyy);

	/**
	 * Format decimal.
	 *
	 * @param number
	 *            the number
	 * @return the string
	 */
	public static String formatDecimal(Number number) {
		String formattedNumber = "";
		if(Objects.nonNull(number)){
			formattedNumber = decimalFormat.format(number);
		}
		return formattedNumber;
	}

	/**
	 * Format date.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatDate(Date date) {
		String formattedDate = "";
		if(Objects.nonNull(date)){
			formattedDate = simpleDateFormat.format(date);
		}
		return formattedDate;
	}

	/**
	 * Format DOB.
	 *
	 * @param date the date
	 * @return the object
	 */
	public static Object formatDOB(Date date) {
		String formattedDate = "";
		if(Objects.nonNull(date)){
			formattedDate = simpleDOBFormat.format(date);
		}
		return formattedDate;
	}
	
	/**
	 * Format DDMMYYYY.
	 *
	 * @param date the date
	 * @return the object
	 */
	public static Object formatDDMMYYYY(Date date) {
		String formattedDate = "";
		if(Objects.nonNull(date)){
			formattedDate = ddMMyyyyFormat.format(date);
		}
		return formattedDate;
	}

}
