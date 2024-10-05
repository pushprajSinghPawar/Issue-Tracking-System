package com.example.ExpertMicroservice.externalservices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ExpertMicroservice.model.Feedback;
import com.example.ExpertMicroservice.model.Issue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IssueDataMicroServices.
 */
@FeignClient(name = "IssueDataMicroService", url = "http://localhost:8083") // Assuming the URL of the IssueDataMicroservice
public interface IssueDataMicroServices {
	
	/**
	 * Adds the issue.
	 *
	 * @param issue the issue
	 * @return the issue
	 */
	@PostMapping("/issue/add")
	Issue addIssue(@RequestBody Issue issue);
	
	/**
	 * Gets the issues by user id.
	 *
	 * @param userid the userid
	 * @return the issues by user id
	 */
	@GetMapping("/issue/getall/{userid}")
	List<Issue> getIssuesByUserId(@PathVariable String userid);
	
	/**
	 * Gets the feedbacks by expertid.
	 *
	 * @param expertid the expertid
	 * @return the feedbacks by expertid
	 */
	@GetMapping("/feedback/byexpertid/{expertid}")
	List<Feedback> getFeedbacksByExpertid(@PathVariable String expertid);
	
	/**
	 * Gets the feedbacks.
	 *
	 * @return the feedbacks
	 */
	@GetMapping("/feedback/all")
	List<Feedback> getFeedbacks();
	
	/**
	 * Editfeedback.
	 *
	 * @param feedbackid the feedbackid
	 * @param feedbackDescription the feedback description
	 * @return the feedback
	 */
	@PutMapping("/feedback/edit/{feedbackid}/{feedbackDescription}")
	Feedback editfeedback(@PathVariable String feedbackid, @PathVariable String feedbackDescription);
	
	/**
	 * Gets the issues by expert id.
	 *
	 * @param expertid the expertid
	 * @return the issues by expert id
	 */
	@GetMapping("/issue/getbyexpertid/{expertid}")
	List<Issue> getIssuesByExpertId(@PathVariable String expertid);

	/**
	 * Gets the by expert id resolved.
	 *
	 * @param expertid the expertid
	 * @return the by expert id resolved
	 */
	@GetMapping("/getbyexpertid/{expertid}/resolved")
	List<Issue> getByExpertIdResolved(@PathVariable String expertid);

	/**
	 * Gets the by expert id un resolved.
	 *
	 * @param expertid the expertid
	 * @return the by expert id un resolved
	 */
	@GetMapping("/getbyexpertid/{expertid}/unresolved")
	List<Issue> getByExpertIdUnResolved(@PathVariable String expertid);
}
