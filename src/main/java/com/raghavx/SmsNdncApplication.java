package com.raghavx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class SmsNdncApplication.
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SmsNdncApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SmsNdncApplication.class, args);
	}
}
