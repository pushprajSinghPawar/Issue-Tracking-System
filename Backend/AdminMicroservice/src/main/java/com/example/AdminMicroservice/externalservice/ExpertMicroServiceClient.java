package com.example.AdminMicroservice.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AdminMicroservice.model.Expert;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExpertMicroServiceClient.
 *
 * @category feign client external service
 * request for response from the expert micro-service
 */
@FeignClient(name = "ExpertMicroservice", url = "http://localhost:8085")
public interface ExpertMicroServiceClient {
	
	/**
	 * Adds the expert.
	 *
	 * @param expert the expert
	 * @return the expert
	 * @category method
	 * calls the expert micro-service to add the expert type of user in the system
	 * it is controller method of  expert micro-service
	 */
	@PostMapping("/expert/register")
	Expert addExpert(@RequestBody Expert expert);
}
