package com.example.ExpertMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpertMicroserviceApplication.
 */
@SpringBootApplication
@EnableFeignClients
public class ExpertMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ExpertMicroserviceApplication.class, args);
	}

}
