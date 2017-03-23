package com.raghavx.ndnc.filter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.raghavx.common.aws.s3.service.AwsS3Service;
import com.raghavx.common.constant.ProcessingStatus;
import com.raghavx.common.data.model.UserFile;
import com.raghavx.common.data.service.FileService;
import com.raghavx.common.data.service.TempFileForCleanUpService;
import com.raghavx.common.exception.InSufficientBalanceException;
import com.raghavx.common.filter.service.FilterService;
import com.raghavx.common.service.reader.DataReaderService;
import com.raghavx.common.utils.DataFormaterUtil;
import com.raghavx.common.utils.CommonUtils;
import com.raghavx.ndnc.common.service.DataFilterService;

/**
 * The Class DndPhoneFilterService.
 */
@Service
public class DndPhoneFilterService implements FilterService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(DndPhoneFilterService.class);

	/** The data filter service. */
	@Autowired
	private DataFilterService dataFilterService;

	/** The fileservice. */
	@Autowired
	private FileService fileservice;

	/** The data reader service. */
	@Autowired
	private DataReaderService dataReaderService;
	
	/** The temp file for clean up service. */
	@Autowired
	private TempFileForCleanUpService tempFileForCleanUpService;

	/** The aws S 3 service. */
	@Autowired
	private AwsS3Service awsS3Service;

	/** The aws root bucket. */
	@Value("${ndnc.aws.root.bucket}")
	private String awsRootBucket;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Async
	public void filterFile(Long fileId,Long userId, File rawFile) {
		logger.info("Started filterFile for user id {} ",userId);	
		
		long start = System.currentTimeMillis();

		UserFile file = fileservice.findById(fileId);
		Date ts = new Date();
		
		StringBuilder pathBuilder = new StringBuilder();
		
		pathBuilder.append(awsRootBucket).append(userId).append("/").append(file.getFileId()).append("/").append(DataFormaterUtil.formatDDMMYYYY(ts));

		String rawFilePath = pathBuilder.toString();
		String dndFilteredFilePath = rawFilePath+"/dnd";
		String nonDndFilteredFilePath = rawFilePath+"/nondnd";
		
		// uploading the raw file to s3
		try {
			tempFileForCleanUpService.saveTempFile(rawFile);
			awsS3Service.upload(CommonUtils.enrichFilePath(rawFile.getAbsolutePath()),rawFilePath, "raw.csv");
			file.setRawFilePath(awsS3Service.getPublicURL(rawFilePath,"raw.csv"));
		} catch (IOException e) {
			file.setUpdatedOn(new Date());
			file.setStatus(ProcessingStatus.UPLOAD_FAILED);
			file = fileservice.save(file);
			return;
		}		

		List<File> dndRecords = new ArrayList<>();
		List<File> nonDndRecords = new ArrayList<>();
		
		List<Future<File>> dndFutures = new ArrayList<>();
		List<Future<File>> nonDndFutures = new ArrayList<>();

		try {
			File[] splitFiles = dataReaderService.splitAndRead(userId,rawFile);
			// save temp files that can be cleaned up later
			tempFileForCleanUpService.saveTempFiles(splitFiles);
			
			for (int i = 0; i < splitFiles.length; i++) {
				dndFutures.add(
						dataFilterService.filterDNDNumber(splitFiles[i], splitFiles[i].getName()));
				nonDndFutures.add(
						dataFilterService.filterNonDNDNumber(splitFiles[i], splitFiles[i].getName()+System.currentTimeMillis()));				
			}			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InSufficientBalanceException isbe) {
			file.setStatus(ProcessingStatus.INSUFFICIENT_BALANCE);
			file.setUpdatedOn(new Date());
			file.setTotalNumbers(isbe.getRecords());
			fileservice.save(file);
			return;
		}
		
		for(Future<File> fut : dndFutures){
			try {
				dndRecords.add(fut.get());
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Error in aggregating the dnd files {}"+e);
				e.printStackTrace();
			}
		}
		
		for(Future<File> fut : nonDndFutures){
			try {
				nonDndRecords.add(fut.get());
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Error in aggregating the dnd files {}"+e);
				e.printStackTrace();
			}
		}

		
		// dndRecords & nonDndRecords has list of files
		// now these should be used for counting total , dnd and non dnd records
		// these should be deleted after writing to s3.
		// concanate all the files
		tempFileForCleanUpService.saveTempFiles(dndRecords);
		tempFileForCleanUpService.saveTempFiles(nonDndRecords);

		File dndRecordsFile = null, nonDndrecordsFile = null;
		try {
			dndRecordsFile = File.createTempFile("dndRecords", "csv");
			nonDndrecordsFile = File.createTempFile("nonDndRecords", "csv");
			tempFileForCleanUpService.saveTempFile(dndRecordsFile);
			tempFileForCleanUpService.saveTempFile(nonDndrecordsFile);
		} catch (Exception ex) {

		}		

		long totalDND = concanateAllFiles(dndRecords, dndRecordsFile);
		long totalNonDND = concanateAllFiles(nonDndRecords, nonDndrecordsFile);
		
		// uploading the processed file to s3
		try {
			awsS3Service.upload(dndRecordsFile.getAbsolutePath(),dndFilteredFilePath, "dnd.csv");
			awsS3Service.upload(nonDndrecordsFile.getAbsolutePath(),nonDndFilteredFilePath, "nondnd.csv");

			file.setDndFilteredFilePath(awsS3Service.getPublicURL(dndFilteredFilePath,"dnd.csv"));
			file.setNonDndFilteredFilePath(awsS3Service.getPublicURL(nonDndFilteredFilePath,"nondnd.csv"));
			
		} catch (IOException e) {
			file.setUpdatedOn(new Date());
			file.setStatus(ProcessingStatus.PROCESSED_FILE_UPLOAD_FAILED);
			file = fileservice.save(file);
			return;
		}

		file.setRawFilePath(awsS3Service.getPublicURL(rawFilePath,"raw.csv"));
		file.setDndNumbers(totalDND);
		file.setNonDndNumbers(totalNonDND);
		file.setTotalNumbers(totalNonDND + totalDND);
		file.setStatus(ProcessingStatus.COMPLETED);
		file.setUpdatedOn(new Date());
		fileservice.save(file);		
		logger.info("Time taken in filter of user {} and file id {} is {}",userId,file.getFileId(),(System.currentTimeMillis()-start));
	}

	/**
	 * Concanate all files.
	 *
	 * @param records the records
	 * @param recordsToBeWriten the records to be writen
	 * @return the long
	 */
	private long concanateAllFiles(List<File> records, File recordsToBeWriten) {
		FileWriter fw = null;
		try {

			fw = new FileWriter(recordsToBeWriten);
			long totalrecords = 0;
			for (File f : records) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = br.readLine();
				while (line != null) {
					totalrecords++;
					fw.write(line + '\n');
					line = br.readLine();
				}

				try {
					br.close();
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			return totalrecords;
		} catch (IOException e) {
			return 0;
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (Exception ex) {
			}
		}

	}

}
