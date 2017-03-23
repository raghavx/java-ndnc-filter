package com.raghavx.common.aws.s3.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.raghavx.common.aws.s3.service.AwsS3Service;

/**
 * The Class AwsS3ServiceImpl.
 */
@Service
public class AwsS3ServiceImpl implements AwsS3Service {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AwsS3ServiceImpl.class);

	/** The aws root bucket. */
	@Value("${ndnc.aws.root.bucket}")
	private String awsRootBucket;

	/** The amazon S 3. */
	private AmazonS3 amazonS3;

	/** The transfer manager. */
	private TransferManager transferManager;

	/** The resource loader. */
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * Instantiates a new aws S 3 service impl.
	 *
	 * @param amazonS3 the amazon S 3
	 */
	@Autowired
	public AwsS3ServiceImpl(AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
		if (transferManager == null) {
			transferManager = new TransferManager(amazonS3);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void download(String filePath) throws IOException {

		Resource resource = this.resourceLoader.getResource(filePath);
		Long start = System.currentTimeMillis();
		InputStream inputStream = resource.getInputStream();

		logger.debug("input stream : {}", inputStream);
		// read file

		logger.info("Time take is : {} ", (System.currentTimeMillis() - start));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void upload(String filePath) throws IOException {
		Long start = System.currentTimeMillis();
		Resource resource = this.resourceLoader.getResource("s3://ratn-bucket/zip/upload.csv");
		WritableResource writableResource = (WritableResource) resource;
		Path path = Paths.get(filePath);
		byte[] data = Files.readAllBytes(path);
		try (OutputStream outputStream = writableResource.getOutputStream()) {
			outputStream.write(data);
		}

		transferManager.upload("ratn-bucket", "filename", new File(filePath));

		logger.info("Time take is : {} ", (System.currentTimeMillis() - start));
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public Upload upload(String filePath, String bucket, String fileNameKey) throws IOException {
		AmazonS3 amazonS3xClient = transferManager.getAmazonS3Client();
		System.out.println(amazonS3xClient.getRegion());
		return transferManager.upload(bucket, fileNameKey, new File(filePath));
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public String getPublicURL(String bucket, String fileKey) {
		Date d = new Date();
		d.setTime(d.getTime() + (1000 * 24 * 60 * 60 * 7));
		URL url = this.amazonS3.getUrl(bucket, fileKey);
		
		return url.toString();

	}

}
