package com.raghavx.common.service.reader;

import java.io.File;
import java.io.IOException;

import com.raghavx.common.exception.InSufficientBalanceException;

/**
 * The Interface DataReaderService.
 */
public interface DataReaderService {
	
	/**
	 * Split and read.
	 *
	 * @param userId the user id
	 * @param file the file
	 * @return the file[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InSufficientBalanceException the in sufficient balance exception
	 */
	public File[] splitAndRead(Long userId,File file) throws IOException,InSufficientBalanceException;
	
}
