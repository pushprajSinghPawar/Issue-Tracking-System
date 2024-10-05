package com.example.UserMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO: Auto-generated Javadoc
/**
 * The Class UserMicroserviceApplication.
 */
@SpringBootApplication 
@EnableDiscoveryClient(autoRegister = true)
@EnableFeignClients
public class UserMicroserviceApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}
}