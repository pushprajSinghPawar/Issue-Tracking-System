package com.example.IssueDataMicroservice.externalService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.IssueDataMicroservice.model.Expert;
import com.example.IssueDataMicroservice.model.Issue;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExpertMicroServiceClient.
 */
@FeignClient(name = "ExpertMicroservice", url = "http://localhost:8085")
public interface ExpertMicroServiceClient {
	
	/**
	 * All experts.
	 *
	 * @return the list
	 */
	@GetMapping("/expert/all")
	List<Expert> allExperts();
	
	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return the expert
	 */
	@PutMapping("/addissue")
	Expert addissue(@RequestBody Issue issue);
}
