package com.example.UserMicroservice.service;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.UserMicroservice.exception.ExpertNotFound;
import com.example.UserMicroservice.model.Feedback;
import com.example.UserMicroservice.model.Issue;
import com.example.UserMicroservice.model.PaymentInfo;
import com.example.UserMicroservice.model.User;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws Exception the exception
	 */
	public User addUser(User user) throws Exception;
	
	/**
	 * Adds the issue.
	 *
	 * @param issue the issue
	 * @param userid the userid
	 * @return the string
	 * @throws ExpertNotFound the expert not found
	 */
	public String addIssue(Issue issue, String userid) throws ExpertNotFound;
	
	/**
	 * View all issues.
	 *
	 * @param userid the userid
	 * @param pageNumber 
	 * @param pageSize 
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Issue> viewAllIssues(String userid, Integer pageSize, Integer pageNumber) throws Exception;
	
	/**
	 * View resolvedissue.
	 *
	 * @param userid the userid
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Issue> viewResolvedissue(String userid)throws Exception;
	
	/**
	 * View un resolvedissue.
	 *
	 * @param userid the userid
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Issue> viewUnResolvedissue(String userid)throws Exception;
	
	/**
	 * Viewfeedbackbyid.
	 *
	 * @param feedbackid the feedbackid
	 * @return the feedback
	 */
	public Feedback viewfeedbackbyid(String feedbackid);
	
	/**
	 * Gets the allspecs.
	 *
	 * @return the allspecs
	 */
	public List<String> getallspecs();

	/**
	 * Save the PaymentInfo.
	 *
	 * @return the PaymentInfo
	 */
	public PaymentInfo savePayment(@Valid PaymentInfo paymentInfo);

	/**
	 * return the Paid Issues.
	 *
	 * @return the Paid Issues
	 */
	public List<PaymentInfo> paidIssues();

	public User getUserById(String userid);

	public Issue editIssue(String issueid, String issuedescription);
}