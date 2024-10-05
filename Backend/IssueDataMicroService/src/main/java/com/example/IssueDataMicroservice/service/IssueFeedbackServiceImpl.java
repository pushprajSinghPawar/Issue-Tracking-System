package com.example.IssueDataMicroservice.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.IssueDataMicroservice.externalService.ExpertMicroServiceClient;
import com.example.IssueDataMicroservice.model.Feedback;
import com.example.IssueDataMicroservice.model.Issue;
import com.example.IssueDataMicroservice.repository.FeedbackRepository;
import com.example.IssueDataMicroservice.repository.IssueRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class IssueFeedbackServiceImpl.
 */
@Service
public class IssueFeedbackServiceImpl implements IssueFeedbackService  {
	
	/** The issue repository. */
	@Autowired 
	IssueRepository issueRepository;
	
	/** The feedback repository. */
	@Autowired
	FeedbackRepository feedbackRepository;
	
	/** The expert micro service client. */
	@Autowired
	ExpertMicroServiceClient expertMicroServiceClient;
	
	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return the issue
	 * @throws Exception the exception
	 */
	public Issue addissue(Issue issue) throws Exception{
		issue.setIssueid(issue.getIssueid().trim()); 
		issue.setSoftwareIssueDescription(issue.getSoftwareIssueDescription().trim());
		issue.setSoftwareIssueTitle(issue.getSoftwareIssueTitle().trim());
		String expertId = issue.getIssueid().split("-")[0];
		Feedback feedback = new Feedback("fb"+issue.getIssueid(),issue.getUserid(),issue.getIssueid(), expertId, "");
		Feedback feedback2 = feedbackRepository.save(feedback);
		if(feedback2.getExpertid().equals(feedback2.getExpertid())==false) {
			throw new Exception("ERROR OCCURED SOMETHING WENT WRONG");
		}
		if(issue.isPaidTicket()) {
			issue.setHighPriority(true);
		}
		return issueRepository.save(issue);
	}
	
	/**
	 * Gets the allissue.
	 *
	 * @param userid the userid
	 * @return the allissue
	 */
	public List<Issue> getallissue(String userid){
		List<Issue> issues=issueRepository.findByUserid(userid);
		return issues;
	}
	
	/**
	 * Gets the by expertidissue.
	 *
	 * @param expertid the expertid
	 * @return the by expertidissue
	 */
	public List<Issue> getByExpertidissue(String expertid){
		List<Issue> issues=issueRepository.findAll();
		List<Issue> issues2 = issues.stream().filter(issue->issue.getIssueid().split("-")[0].equals(expertid)).collect(Collectors.toList());
		return issues2;
	}
	
	/**
	 * Gets the allissue.
	 *
	 * @return the allissue
	 */
	public List<Issue> getallissue(){
		List<Issue> issues=issueRepository.findAll();
		return issues;
	}
	
	/**
	 * Gets the all feedbacks.
	 *
	 * @return the all feedbacks
	 */
	public List<Feedback> getAllFeedbacks(){
		return feedbackRepository.findAll();
	}
	
	/**
	 * Gets the all feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all feedbacks byexpert id
	 */
	public List<Feedback> getAllFeedbacksByexpertId(String expertid){
		return feedbackRepository.findByExpertid(expertid);
	}
	
	/**
	 * Gets the all feedbacks by feedbackid.
	 *
	 * @param feedbackid the feedbackid
	 * @return the all feedbacks by feedbackid
	 * @throws Exception the exception
	 */
	public Feedback getAllFeedbacksByFeedbackid(String feedbackid) throws Exception{
		return feedbackRepository.findById(feedbackid).orElseThrow(() -> new Exception("FEEDBACK NOT FOUND"));
	}
	
	/**
	 * Editfeedback.
	 *
	 * @param feedbackid the feedbackid
	 * @param feedbackDescription the feedback description
	 * @return the feedback
	 * @throws Exception the exception
	 */
	public Feedback editfeedback(String feedbackid, String feedbackDescription) throws Exception {
		feedbackDescription = feedbackDescription.trim();
		Feedback feedback = feedbackRepository.findById(feedbackid).orElseThrow(() -> new  Exception("FeedBack Not found with feedback Id "+feedbackid));
		Issue issue = issueRepository.findByIssueid(feedbackid.substring(2));
		issue.setStatus("resolved");
		issueRepository.save(issue);
		feedback.setFeedbackDescription(feedbackDescription);
		feedback.setFeedbackgiven(new Date());
		return feedbackRepository.save(feedback);
	}
	
	/**
	 * Gets the all resolved feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all resolved feedbacks byexpert id
	 * @throws Exception the exception
	 */
	public List<Issue> getAllResolvedFeedbacksByexpertId(String expertid) throws Exception {
		
		List<Issue> issues=issueRepository.findAll();
		issues = issues.stream().filter(issue->issue.getIssueid().split("-")[0].equals(expertid) && issue.getStatus().equalsIgnoreCase("resolved")).collect(Collectors.toList());
		if(issues==null || issues.isEmpty()) {
			throw new Exception("NO ISSUES ROGHT NOW FOR EXPERT WITH EXPERT ID"+expertid);
		}
		return issues;
	}
	
	/**
	 * Gets the all un resolved feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all un resolved feedbacks byexpert id
	 * @throws Exception the exception
	 */
	public List<Issue> getAllUnResolvedFeedbacksByexpertId(String expertid) throws Exception {
		
		List<Issue> issues=issueRepository.findAll();
		issues = issues.stream().filter(issue->issue.getIssueid().split("-")[0].equals(expertid) && issue.getStatus().equalsIgnoreCase("resolved")==false).collect(Collectors.toList());
		if(issues==null || issues.isEmpty()) {
			throw new Exception("NO ISSUES ROGHT NOW FOR EXPERT WITH EXPERT ID"+expertid);
		}
		return issues;
	}
	
	/**
	 * Gets the feedback.
	 *
	 * @param feedbackid the feedbackid
	 * @return the feedback
	 */
	@Override
	public Feedback getFeedback(String feedbackid) {
		// TODO Auto-generated method stub
		return feedbackRepository.findById(feedbackid).orElse(null);
	}

	@Override
	public Issue editIssue(String issueid, String issuedescription) {
		// TODO Auto-generated method stub
		issuedescription=issuedescription.trim();
		Issue issue = issueRepository.findByIssueid(issueid);
		issue.setSoftwareIssueDescription(issuedescription);
		return issueRepository.save(issue);
	}
}
