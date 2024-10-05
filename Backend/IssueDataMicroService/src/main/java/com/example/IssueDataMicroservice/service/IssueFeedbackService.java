package com.example.IssueDataMicroservice.service;

import java.util.List;
import com.example.IssueDataMicroservice.model.Feedback;
import com.example.IssueDataMicroservice.model.Issue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IssueFeedbackService.
 */
public interface IssueFeedbackService {
	
	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return the issue
	 * @throws Exception the exception
	 */
	public Issue addissue(Issue issue) throws Exception;
	
	/**
	 * Gets the allissue.
	 *
	 * @param userid the userid
	 * @return the allissue
	 */
	public List<Issue> getallissue(String userid);
	
	/**
	 * Gets the by expertidissue.
	 *
	 * @param expertid the expertid
	 * @return the by expertidissue
	 */
	public List<Issue> getByExpertidissue(String expertid);
	
	/**
	 * Gets the allissue.
	 *
	 * @return the allissue
	 */
	public List<Issue> getallissue();
	
	/**
	 * Gets the all feedbacks.
	 *
	 * @return the all feedbacks
	 */
	public List<Feedback> getAllFeedbacks();
	
	/**
	 * Gets the all feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all feedbacks byexpert id
	 */
	public List<Feedback> getAllFeedbacksByexpertId(String expertid);
	
	/**
	 * Gets the all feedbacks by feedbackid.
	 *
	 * @param feedbackid the feedbackid
	 * @return the all feedbacks by feedbackid
	 * @throws Exception the exception
	 */
	public Feedback getAllFeedbacksByFeedbackid(String feedbackid) throws Exception;
	
	/**
	 * Editfeedback.
	 *
	 * @param feedbackid the feedbackid
	 * @param feedbackDescription the feedback description
	 * @return the feedback
	 * @throws Exception the exception
	 */
	public Feedback editfeedback(String feedbackid, String feedbackDescription) throws Exception;
	
	/**
	 * Gets the all resolved feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all resolved feedbacks byexpert id
	 * @throws Exception the exception
	 */
	public List<Issue> getAllResolvedFeedbacksByexpertId(String expertid) throws Exception;
	
	/**
	 * Gets the all un resolved feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all un resolved feedbacks byexpert id
	 * @throws Exception the exception
	 */
	public List<Issue> getAllUnResolvedFeedbacksByexpertId(String expertid) throws Exception;
	
	/**
	 * Gets the feedback.
	 *
	 * @param feedbackid the feedbackid
	 * @return the feedback
	 */
	public Feedback getFeedback(String feedbackid);

	
	/**
	 * Edit issue.
	 *
	 * @param issueid the string
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public Issue editIssue(String issueid, String issuedescription);
}
