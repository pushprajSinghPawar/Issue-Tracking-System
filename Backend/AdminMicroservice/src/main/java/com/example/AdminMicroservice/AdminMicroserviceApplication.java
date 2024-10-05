package com.example.AdminMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminMicroserviceApplication.
 */
@SpringBootApplication
@EnableFeignClients
public class AdminMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AdminMicroserviceApplication.class, args);
	}

}
