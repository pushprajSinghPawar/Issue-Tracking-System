package com.example.ExpertMicroservice.controller;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ExpertMicroservice.model.EditFeedbackModel;
import com.example.ExpertMicroservice.model.Expert;
import com.example.ExpertMicroservice.model.Feedback;
import com.example.ExpertMicroservice.model.Issue;
import com.example.ExpertMicroservice.service.ExpertService;
import com.example.ExpertMicroservice.service.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpertController.
 */
@RestController
@RequestMapping("/expert")
public class ExpertController {
	
	/** The expert service. */
	@Autowired
	ExpertService expertService;
	
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(ExpertController.class);
	
	/** The jwt service. */
	@Autowired
	JwtService jwtService;
	
	
	/**
	 * Adds the expert.
	 *
	 * @param expert the expert
	 * @return the response entity
	 * @throws Exception the exception
	 */
	//role of admin
	@PostMapping("/register")
	@Operation(summary = "adding expert  in the database")
	public ResponseEntity<Expert> addExpert(@Valid @RequestBody Expert expert) throws Exception {
		Expert expert2 = expertService.addExpert(expert);
		
		if(expert2!=null) {
			logger.info("Expert Registerd id "+expert2.getExpertid());
		}else {
			logger.error("Expert Not Registerd");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(expert2);
	}
	

	/**
	 * Addissue.
	 *
	 * @param issue the issue
	 * @return the response entity
	 * @throws Exception the exception
	 */
	//role of user
	@PutMapping("/addissue")
	@Operation(summary = "adding issue in the databse")
	public ResponseEntity<Boolean> addissue(@RequestBody Issue issue) throws Exception{
		Boolean boolean1 = expertService.addissue(issue);
		if(boolean1) {
			logger.info("Issue added with id "+issue.getIssueid());
		}else {
			logger.error("Issue not added");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(boolean1);
	}
	
	/**
	 * Gets the all feedbacks by expert id.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the all feedbacks by expert id
	 * @throws Exception the exception
	 */
	//role of expert
	@GetMapping("/getfeedbacks")
	@Operation(summary = "get all feedbacks from the database by expert id")
	public ResponseEntity<List<Feedback>> getAllFeedbacksByExpertId(@RequestHeader("Authorization") String authorizationHeader) throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String expertid = (String) userinfo.get("credId");
		
		List<Feedback> feedbacks = expertService.getAllFeedbacksByExpertId(expertid);
		if(feedbacks==null || feedbacks.isEmpty()) {
			logger.error("Feedbacks not able to fetch  or are empty");
		}else {
			logger.info("Feedbacks fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedbacks);
	}
	
	/**
	 * Editfeedback.
	 *
	 * @param issueid the issueid
	 * @param editFeedbackModel the edit feedback model
	 * @param authorizationHeader the authorization header
	 * @return the response entity
	 * @throws Exception the exception
	 */
	//role of expert
	@PutMapping("/editfeedback/{issueid}")
	@Operation(summary = "edit feedback from the database by expert id and the issue id")
	public ResponseEntity<Feedback> editfeedback(@PathVariable String issueid,@Valid @RequestBody EditFeedbackModel editFeedbackModel ,@RequestHeader("Authorization") String authorizationHeader) throws Exception {
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String expertid = (String) userinfo.get("credId");
		String feedbackDescription =  editFeedbackModel.getFeedbackDescription();
		Feedback feedback = expertService.editfeedback(issueid, feedbackDescription, expertid);
		if(feedback==null) {
			logger.error("Feedback not able to update");
		}else {
			logger.info("Feedbacks updated");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedback);
	}

	/**
	 * Gets the issue by expert id.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the issue by expert id
	 * @throws Exception the exception
	 */
	//role of expert
	@GetMapping("/getissues")
	@Operation(summary = "get all issues from the database by expert id")
	public ResponseEntity<List<Issue>>  getissueByExpertId(@RequestHeader("Authorization") String authorizationHeader) throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String expertid = (String) userinfo.get("credId");
		
		List<Issue> issues = expertService.getissueByExpertId(expertid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issues are not there or not able to fetch");
		}else {
			logger.info("Issues successfully fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	

	/**
	 * Gets the resolved issue.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the resolved issue
	 * @throws Exception the exception
	 */
	//role of expert
	@GetMapping("/issuestatus/resolved")
	@Operation(summary = "get all resolved issues from the database by expert id")
	public ResponseEntity<List<Issue>>  getResolvedIssue(@RequestHeader("Authorization") String authorizationHeader) throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String expertid = (String) userinfo.get("credId");
		
		List<Issue> issues = expertService.getResolvedIssue(expertid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issues are not there or not able to fetch");
		}else {
			logger.info("Issues successfully fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	

	/**
	 * Gets the un resolved issue.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the un resolved issue
	 * @throws Exception the exception
	 */
	//role of expert
	@GetMapping("/issuestatus/unresolved")
	@Operation(summary = "get all unresolved issues from the database by expert id")
	public ResponseEntity<List<Issue>>  getUnResolvedIssue(@RequestHeader("Authorization") String authorizationHeader) throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String expertid = (String) userinfo.get("credId");
		
		List<Issue> issues = expertService.getUnResolvedIssue(expertid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issues are not there or not able to fetch"); 
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * Gets the allspecializations.
	 *
	 * @return the allspecializations
	 * @throws Exception the exception
	 */
	@GetMapping("/getallspecializations")
	@Operation(summary = "get all distinct specialization of various experts from the database")
	public	ResponseEntity<List<String>>  getallspecializations() throws Exception{
		List<String> allSpecializationsList = expertService.getallspecs();
		if(allSpecializationsList==null || allSpecializationsList.isEmpty()) {
			logger.error("not able to fetch Specializations List");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allSpecializationsList);
	}   
	@GetMapping("/showdetails")
	@Operation(summary = "get expert's details such as all properties of the expert")
	ResponseEntity<Expert> showUserDetails(@RequestHeader("Authorization") String authorizationHeader){
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String expertid = (String) userinfo.get("credId");
		Expert temp = expertService.getExpertById(expertid);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(temp);
	}
	
}