package com.security.externalClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.model.Expert;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExpertClient.
 */
@FeignClient(name = "ExpertMicroService", url = "http://localhost:8085")
public interface ExpertClient {
	
	/**
	 * Adds the expert.
	 *
	 * @param expert the expert
	 * @return the expert
	 */
	@PostMapping("/expert/register")
	Expert addExpert(@RequestBody Expert expert);

}
