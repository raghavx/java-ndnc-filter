package com.raghavx.common.service.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavx.common.data.service.BillingService;
import com.raghavx.common.data.uo.CreditUO;
import com.raghavx.common.exception.InSufficientBalanceException;

/**
 * The Class DataReaderServiceImpl.
 */
@Service
public class DataReaderServiceImpl implements DataReaderService{

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DataReaderServiceImpl.class);
	
	/** The billing service. */
	@Autowired
	private BillingService billingService;
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("resource")
	@Override
	public File[] splitAndRead(Long userId,File file) throws IOException,InSufficientBalanceException {

		long start = System.currentTimeMillis();

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();

		File[] files = new File[] { File.createTempFile("data0", "csv"), File.createTempFile("data1", "csv"),
				File.createTempFile("data2", "csv"), File.createTempFile("data3", "csv"),
				File.createTempFile("data4", "csv"), File.createTempFile("data5", "csv"),
				File.createTempFile("data6", "csv"), File.createTempFile("data7", "csv") };

		FileWriter[] fws = new FileWriter[] { new FileWriter(files[0]), new FileWriter(files[1]),
				new FileWriter(files[2]), new FileWriter(files[3]), new FileWriter(files[4]), new FileWriter(files[5]),
				new FileWriter(files[6]), new FileWriter(files[7]) };

		fws[0].write(line + '\n');
		int b = 0;
		long records = 1;
		while (line != null) {
			records++;
			b++;
			if (b == 8) {
				b = 0;
			}
			fws[b].write(line + '\n');

			line = br.readLine();
		}
		
		CreditUO credit = billingService.availableCredit(userId);
		
		if(records > credit.getCredit()){
			throw new InSufficientBalanceException("Insufficient Balance for user id, please contact Admin : "+userId, "Insufficient Balance , please contact Admin.",records);
		}
		
		logger.info("Time taken in splitting the file {} ",(System.currentTimeMillis()-start));

		br.close();
		for (FileWriter fw : fws) {
			fw.close();
		}
		return files;	
	}

	
}
