package com.example.ExpertMicroservice.externalservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ExpertMicroservice.model.credentials;



// TODO: Auto-generated Javadoc
/**
 * The Interface SecurtiyMicroserviceClient.
 */
@FeignClient(name = "SecurityService", url = "http://localhost:9092")
public interface SecurtiyMicroserviceClient {

	/**
	 * Adds the user.
	 *
	 * @param credential the credential
	 * @return the credentials
	 */
	@PostMapping("/auth/addCred")
	public credentials
	addUser(@RequestBody credentials credential);
		
		
}
