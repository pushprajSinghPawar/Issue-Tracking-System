package com.example.UserMicroservice.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.UserMicroservice.model.Issue;
// TODO: Auto-generated Javadoc

/**
 * The Interface ExpertMicroServicesClient.
 */
@FeignClient(name = "ExpertMicroservice", url = "http://localhost:8085") // Assuming the URL of the IssueDataMicroservice
public interface ExpertMicroServicesClient {
	
	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return true, if successful
	 */
	@PutMapping("/expert/addissue")
	boolean addissue(@RequestBody Issue issue);

	/**
	 * Gets the allspecializations.
	 *
	 * @return the allspecializations
	 */
	@GetMapping("/expert/getallspecializations")
	List<String> getallspecializations();
}
