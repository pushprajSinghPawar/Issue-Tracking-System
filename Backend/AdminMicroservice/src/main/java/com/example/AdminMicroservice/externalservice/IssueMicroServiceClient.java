package com.example.AdminMicroservice.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.AdminMicroservice.model.Feedback;
import com.example.AdminMicroservice.model.Issue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IssueMicroServiceClient.
 *
 * @category feign client external service
 * request for response from the issue data micro-service
 */
@FeignClient(name = "IssueDataMicroservice", url = "http://localhost:8083")
public interface IssueMicroServiceClient {
	
	/**
	 * Gets the allissue.
	 *
	 * @return the allissue
	 * @category feign client external service
	 * request for response from the issue-data micro-service  to get all issues from the issues database for analytics
	 */
	@GetMapping("/issue/getall")
	List<Issue> getallissue();
	
	/**
	 * Gets the all feedbacks.
	 *
	 * @return the all feedbacks
	 * @category feign client external service
	 * request for response from the issue-data micro-service  to get all feedbacks from the issues database for analytics
	 */
	@GetMapping("/feedback/all")
	List<Feedback> getallFeedbacks();
}
