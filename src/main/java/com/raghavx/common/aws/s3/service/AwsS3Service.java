package com.raghavx.common.aws.s3.service;

import java.io.IOException;

import com.amazonaws.services.s3.transfer.Upload;

/**
 * The Interface AwsS3Service.
 */
public interface AwsS3Service {

	/**
	 * Download.
	 *
	 * @param filePath the file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void download(String filePath) throws IOException;

	/**
	 * Upload.
	 *
	 * @param filePath the file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void upload(String filePath) throws IOException;

	/**
	 * Upload.
	 *
	 * @param filePath the file path
	 * @param bucket the bucket
	 * @param fileName the file name
	 * @return 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	Upload upload(String filePath, String bucket, String fileNameKey) throws IOException;
	
	String getPublicURL(String bucket, String fileKey);
}
