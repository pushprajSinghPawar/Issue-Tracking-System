package com.example.IssueDataMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO: Auto-generated Javadoc
/**
 * The Class IssueDataMicroServiceApplication.
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
@EnableFeignClients
public class IssueDataMicroServiceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(IssueDataMicroServiceApplication.class, args);
	}

}
