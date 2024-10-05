package com.example.ExpertMicroservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ExpertMicroservice.model.Expert;
import com.example.ExpertMicroservice.model.Feedback;
import com.example.ExpertMicroservice.model.Issue;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExpertService.
 */
@Service
public interface ExpertService {
	
	/**
	 * Adds the expert.
	 *
	 * @param expert the expert
	 * @return the expert
	 * @throws Exception the exception
	 */
	public Expert addExpert( Expert expert) throws Exception;
	
	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean addissue(Issue issue) throws Exception;
	
	/**
	 * Gets the all feedbacks by expert id.
	 *
	 * @param expertid the expertid
	 * @return the all feedbacks by expert id
	 * @throws Exception the exception
	 */
	public List<Feedback> getAllFeedbacksByExpertId(String expertid)throws Exception;
	
	/**
	 * Editfeedback.
	 *
	 * @param issueid the issueid
	 * @param feedbackDescription the feedback description
	 * @param expertid the expertid
	 * @return the feedback
	 * @throws Exception the exception
	 */
	public Feedback editfeedback(String issueid, String feedbackDescription, String expertid) throws Exception;
	
	/**
	 * Gets the issue by expert id.
	 *
	 * @param expertid the expertid
	 * @return the issue by expert id
	 * @throws Exception the exception
	 */
	public List<Issue> getissueByExpertId(String expertid)throws Exception;
	
	/**
	 * Gets the resolved issue.
	 *
	 * @param expertid the expertid
	 * @return the resolved issue
	 * @throws Exception the exception
	 */
	public List<Issue> getResolvedIssue(String expertid)throws Exception;
	
	/**
	 * Gets the un resolved issue.
	 *
	 * @param expertid the expertid
	 * @return the un resolved issue
	 * @throws Exception the exception
	 */
	public List<Issue> getUnResolvedIssue(String expertid)throws Exception;
	
	/**
	 * Gets the allspecs.
	 *
	 * @return the allspecs
	 */
	public List<String> getallspecs();

	public Expert getExpertById(String expertid);

}
