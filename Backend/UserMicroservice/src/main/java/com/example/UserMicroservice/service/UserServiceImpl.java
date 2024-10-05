package com.example.UserMicroservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.UserMicroservice.exception.ExpertNotFound;
import com.example.UserMicroservice.externalServices.ExpertMicroServicesClient;
import com.example.UserMicroservice.externalServices.IssueMicroServiceClient;
import com.example.UserMicroservice.externalServices.SecurtiyMicroserviceClient;
import com.example.UserMicroservice.model.Feedback;
import com.example.UserMicroservice.model.Issue;
import com.example.UserMicroservice.model.PaymentInfo;
import com.example.UserMicroservice.model.User;
import com.example.UserMicroservice.model.credentials;
import com.example.UserMicroservice.repository.AuthRepo;
import com.example.UserMicroservice.repository.PaymentInfoRepository;
import com.example.UserMicroservice.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	/** The user repository. */
	@Autowired
	UserRepository userRepository;
	
	/** The ab. */
	@Autowired
	SecurtiyMicroserviceClient ab;

	/** The issue micro service client. */
	@Autowired
	IssueMicroServiceClient issueMicroServiceClient;
	
	/** The expert micro services client. */
	@Autowired
	ExpertMicroServicesClient expertMicroServicesClient;
	
	/** The auth repo. */
	@Autowired
	AuthRepo authRepo;
	
	
	/** The Payment Info Repository. */
	@Autowired
	PaymentInfoRepository paymentInfoRepository;

	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws Exception the exception
	 */
	public User addUser(User user) throws Exception {
		String userid = user.getUserid();
		if (userRepository.findById(userid).isPresent()) {
			throw new Exception("USER WITH " + userid + " already exists");
		}
		user.setIssues(Arrays.asList());
		user.setFeedBacks(Arrays.asList());
		user.setRoles(Arrays.asList("user"));
		user = userRepository.save(user);
		if (user != null) {
			ab.addUser(new credentials((user.getUserid()), user.getName(), user.getPassword(), user.getRoles().get(0)));
		}
		return user;
	}

	/**
	 * Adds the issue.
	 *
	 * @param issue the issue
	 * @param userid the userid
	 * @return the string
	 * @throws ExpertNotFound the expert not found
	 */
	public String addIssue(Issue issue, String userid) throws  ExpertNotFound {
		issue.setUserid(userid);
		if(issue.getStatus()==null) {
			issue.setStatus("unresolved");
		}
		if (expertMicroServicesClient.addissue(issue) == true) {
			return "Succesfully Submitted Issue ";
		}else {
			throw new ExpertNotFound("no  expert is found for  this", new Date());
		}
	}

	/**
	 * View all issues.
	 *
	 * @param userid the userid
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Issue> viewAllIssues(@PathVariable String userid, Integer pageSize, Integer pageNumber) throws Exception {
		List<Issue> issues =  issueMicroServiceClient.getallissue(userid);
		if(pageNumber*pageSize>=issues.size()) {
			return issues;
		}
		if((pageSize+1)*pageNumber>=issues.size()) {
			return issues.subList(pageSize*pageNumber, issues.size());
		}
		issues = issues.subList(pageSize*pageNumber,(pageSize+1)*pageNumber);
		return issues;
	}

	/**
	 * View resolvedissue.
	 *
	 * @param userid the userid
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Issue> viewResolvedissue(String userid) throws Exception {
		List<Issue> issues = issueMicroServiceClient.getallissue(userid);
		issues = issues.stream().filter(issue -> issue.getStatus().equalsIgnoreCase("resolved"))
				.collect(Collectors.toList());
		return issues;
	}

	/**
	 * View un resolvedissue.
	 *
	 * @param userid the userid
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<Issue> viewUnResolvedissue(String userid) throws Exception {
		List<Issue> issues = issueMicroServiceClient.getallissue(userid);
		issues = issues.stream().filter(issue -> issue.getStatus().equalsIgnoreCase("unresolved"))
				.collect(Collectors.toList());
		return issues;
	}

	/**
	 * Viewfeedbackbyid.
	 *
	 * @param feedbackid the feedbackid
	 * @return the feedback
	 */
	@Override
	public Feedback viewfeedbackbyid(String feedbackid) {
		// TODO Auto-generated method stub
		return issueMicroServiceClient.getfeedbackbyid(feedbackid);
	}

	/**
	 * Gets the allspecs.
	 *
	 * @return the allspecs
	 */
	@Override
	public List<String> getallspecs() {
		// TODO Auto-generated method stub
		return expertMicroServicesClient.getallspecializations();
	}

	/**
	 * Save the PaymentInfo.
	 *
	 * @return the PaymentInfo
	 */
	@Override
	public PaymentInfo savePayment(@Valid PaymentInfo paymentInfo) {
		// TODO Auto-generated method stub
		return paymentInfoRepository.save(paymentInfo);
	}

	@Override
	public List<PaymentInfo> paidIssues() {
		// TODO Auto-generated method stub
		return paymentInfoRepository.findAll();
	}

	@Override
	public User getUserById(String userid) {
		// TODO Auto-generated method stub
		return userRepository.findById(userid).get();
	}

	@Override
	public Issue editIssue(String issueid, String issuedescription) {
		// TODO Auto-generated method stub
		return issueMicroServiceClient.editIssue(issueid, issuedescription);
	}
	
}
