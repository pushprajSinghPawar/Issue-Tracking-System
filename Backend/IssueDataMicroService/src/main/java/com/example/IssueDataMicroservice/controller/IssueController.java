package com.example.IssueDataMicroservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IssueDataMicroservice.model.Issue;
import com.example.IssueDataMicroservice.service.IssueFeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class IssueController.
 */
@RestController
@RequestMapping("/issue")
//@CrossOrigin(origins = "http://localhost:4200")
public class IssueController {

	/** The issue feedback service. */
	@Autowired
	IssueFeedbackService issueFeedbackService;
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(IssueController.class);
	
	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return the response entity
	 * @throws Exception the exception
	 */
	//user
	@PostMapping("/add")
	@Operation(summary = "add issue in the database to be in used by user microservice")
	public	ResponseEntity<Issue> addissue(@Valid @RequestBody Issue issue) throws Exception{
		Issue issue2 = issueFeedbackService.addissue(issue);
		if(issue2==null) {
			logger.error("Issue not added");
		}else {
			logger.info("Issue added with id "+issue2.getIssueid());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issue2);
	}
	
	/**
	 * Gets the allissue.
	 *
	 * @param userid the userid
	 * @return the allissue
	 * @throws Exception the exception
	 */
	@GetMapping("/getall/{userid}")
	@Operation(summary = "get all issue in the database to be in used by user and expert microservice")
	public ResponseEntity<List<Issue>> getallissue(@PathVariable String userid) throws Exception{
		List<Issue> issues=issueFeedbackService.getallissue(userid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issue not fetched or are empty");
		}else {
			logger.info("Issue fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * Gets the by expertidissue.
	 *
	 * @param expertid the expertid
	 * @return the by expertidissue
	 * @throws Exception the exception
	 */
	@GetMapping("/getbyexpertid/{expertid}")
	@Operation(summary = "get issues by expertid  in the database to be in used by expert microservice")
	public ResponseEntity<List<Issue>> getByExpertidissue(@PathVariable String expertid) throws Exception{
		List<Issue> issues = issueFeedbackService.getByExpertidissue(expertid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issue not fetched or are empty");
		}else {
			logger.error("Issue fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * Gets the by expertidissueresolved.
	 *
	 * @param expertid the expertid
	 * @return the by expertidissueresolved
	 * @throws Exception the exception
	 */
	@GetMapping("/getbyexpertid/{expertid}/resolved")
	@Operation(summary = "get issues by expertid that are  resolved  in the database to be in used by expert microservice")
	public ResponseEntity<List<Issue>> getByExpertidissueresolved(@PathVariable String expertid) throws Exception{
		List<Issue> issues = issueFeedbackService.getAllResolvedFeedbacksByexpertId(expertid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issue not fetched or are empty");
		}else {
			logger.info("Issue fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * Gets the by expertidissueunresolved.
	 *
	 * @param expertid the expertid
	 * @return the by expertidissueunresolved
	 * @throws Exception the exception
	 */
	@GetMapping("/getbyexpertid/{expertid}/unresolved")
	@Operation(summary = "get issues by expertid that are  unresolved  in the database to be in used by expert microservice")
	public ResponseEntity<List<Issue>> getByExpertidissueunresolved(@PathVariable String expertid) throws Exception{
		List<Issue> issues = issueFeedbackService.getAllUnResolvedFeedbacksByexpertId(expertid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issue not fetched or are empty");
		}else {
			logger.info("Issue fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * Gets the allissue.
	 *
	 * @return the allissue
	 * @throws Exception the exception
	 */
	@GetMapping("/getall")
	@Operation(summary = "get all issues by expertid   in the database to be in used by expert microservice")
	public ResponseEntity<List<Issue>> getallissue() throws Exception{
		List<Issue> issues= issueFeedbackService.getallissue();
		if(issues==null || issues.isEmpty()) {
			logger.error("Issue not fetched or are empty");
		}else {
			logger.info("Issue fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	
	
	/**
	 * Edit issue.
	 *
	 * @param issueid the string
	 * @return the response entity
	 * @throws Exception the exception
	 */
	//user
	@PutMapping("/edit/{issueid}")
	@Operation(summary = "edit issue in the database to be in used by user and expert microservice")
	public	ResponseEntity<Issue> editIssue(@PathVariable String issueid,@RequestBody String issuedescription) throws Exception{
		Issue issue2 = issueFeedbackService.editIssue(issueid, issuedescription);
		if(issue2==null) {
			logger.error("Issue not edited");
		}else {
			logger.info("Issue edited with id "+issue2.getIssueid());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issue2);
	}
	
}
