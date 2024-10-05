package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiGatewayApplication.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.*")
public class ApiGatewayApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
