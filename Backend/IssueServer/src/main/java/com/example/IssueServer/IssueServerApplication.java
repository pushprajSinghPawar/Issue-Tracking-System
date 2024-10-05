package com.example.IssueServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// TODO: Auto-generated Javadoc
/**
 * The Class IssueServerApplication.
 */
@SpringBootApplication
@EnableEurekaServer
public class IssueServerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(IssueServerApplication.class, args);
	}

}
