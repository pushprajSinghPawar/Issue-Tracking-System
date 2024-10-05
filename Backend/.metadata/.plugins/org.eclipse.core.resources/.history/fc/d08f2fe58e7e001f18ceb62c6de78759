package com.example.UserMicroservice.controller;

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

import com.example.UserMicroservice.exception.ExpertNotFound;
import com.example.UserMicroservice.model.Feedback;
import com.example.UserMicroservice.model.Issue;
import com.example.UserMicroservice.model.PaymentInfo;
import com.example.UserMicroservice.model.User;
import com.example.UserMicroservice.service.JwtService;
import com.example.UserMicroservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	/** The userservice. */
	@Autowired
	UserService userservice;
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/** The jwt service. */
	@Autowired
	JwtService jwtService;
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the response entity
	 * @throws NullPointerException the null pointer exception
	 * @throws Exception the exception
	 */
	@PostMapping("/register")
	@Operation(summary = "Add a new user")
	ResponseEntity<User> addUser(@Valid @RequestBody User user) throws NullPointerException, Exception {
		User user2 = userservice.addUser(user);
		System.out.println(user);
		if(user2==null) {
			logger.info("Not able to register user with user id "+user.getUserid());
		}else {

			logger.info("Registered user with user id "+user.getUserid());
		}
		return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body(user2);
	}
	
	/**
	 * Adds the issue.
	 *
	 * @param issue the issue
	 * @param authorizationHeader the authorization header
	 * @return the response entity
	 * @throws ExpertNotFound the expert not found
	 * @throws Exception the exception
	 */
	@PostMapping("/addissue")
    @Operation(summary = "Add an issue")
	ResponseEntity<String> addIssue(@RequestBody  @Valid Issue issue, @RequestHeader("Authorization") String authorizationHeader) throws ExpertNotFound, Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String userid = (String) userinfo.get("credId");
		String res = userservice.addIssue(issue, userid);
		logger.info(res);
		return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body(res);
	}
	
	/**
	 * View all issues.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping("/issues/{pageSize}/{pageNumber}") 
    @Operation(summary = "get all issues by userid")
	public ResponseEntity<List<Issue>> viewAllIssues(
			@RequestHeader("Authorization") String authorizationHeader
			, @PathVariable(required = false) Integer pageSize
			, @PathVariable(required = false) Integer pageNumber ) throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String userid = (String) userinfo.get("credId");
		if(pageSize==null) {
			pageSize =  5;
		}
		if(pageNumber == null) {
			pageNumber = 1;
		}
		List<Issue> issues = userservice.viewAllIssues(userid, pageSize, pageNumber);
		if(issues==null || issues.isEmpty()) {
			logger.info("Issues are not there or not able to fetch");
		}else {
			logger.error("Issues successfully fetched");
		}
		return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * View resolvedissue.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping("/issues/resolved")
    @Operation(summary = "get all issues that are resolved by userid")
	ResponseEntity<List<Issue>> viewResolvedissue(@RequestHeader("Authorization") String authorizationHeader) throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String userid = (String) userinfo.get("credId");
		
		List<Issue> issues = userservice.viewResolvedissue(userid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issues are not there or not able to fetch");
		}else {
			logger.error("Issues successfully fetched");
		}
		return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * View un resolvedissue.
	 *
	 * @param authorizationHeader the authorization header
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@GetMapping("/issues/unresolved")
    @Operation(summary = "get all issues that are unresolved by userid")
	ResponseEntity<List<Issue>> viewUnResolvedissue(@RequestHeader("Authorization") String authorizationHeader)throws Exception{
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String userid = (String) userinfo.get("credId");
		
		List<Issue> issues = userservice.viewUnResolvedissue(userid);
		if(issues==null || issues.isEmpty()) {
			logger.error("Issues are not there or not able to fetch");
		}else {
			logger.error("Issues successfully fetched");
		}
		return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body(issues);
	}
	
	/**
	 * View feedback.
	 *
	 * @param feedback id the feedback id
	 * @return the response entity
	 */
	@GetMapping("/feedback/{feedbackid}")
    @Operation(summary = "get feedback by feedback id")
	ResponseEntity<Feedback> viewFeedback(@PathVariable String feedbackid){
		Feedback  feedback = userservice.viewfeedbackbyid(feedbackid);
		return ResponseEntity.status(org.springframework.http.HttpStatus.ACCEPTED).body(feedback);
	}

	/**
	 * Gets the all-specializations.
	 *
	 * @return the all-specializations
	 * @throws Exception the exception
	 */
	@GetMapping("/getallspecializations")
	@Operation(summary = "get all distinct specialization of various experts from the database")
	ResponseEntity<List<String>>  getallspecializations() throws Exception{
		List<String> allSpecializationsList = userservice.getallspecs();
		if(allSpecializationsList==null || allSpecializationsList.isEmpty()) {
			logger.error("not able to fetch Specializations List");
		}else {
			logger.info("Specializations successfully fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allSpecializationsList);
	}
	
	/**
	 * save the payment info.
	 *
	 * @return the payment info only
	 */
	@PostMapping("/savepaymentinfo")
	@Operation(summary = "save payment info such transaction id and userid into the the database")
	ResponseEntity<PaymentInfo> savePaymentInfo(@Valid @RequestBody PaymentInfo paymentInfo){
		PaymentInfo paymentInfo2 = userservice.savePayment(paymentInfo);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentInfo2);
	}
	
	@GetMapping("/getallpaidissues")
	@Operation(summary = "get all paid issues")
	ResponseEntity<List<PaymentInfo>> getAllPaidIssues(){ 
		List<PaymentInfo> paidIssues = userservice.paidIssues();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(paidIssues);
	}
	@GetMapping("/showdetails")
	@Operation(summary = "get user's details such as all properties of the user")
	ResponseEntity<User> showUserDetails(@RequestHeader("Authorization") String authorizationHeader){
		Map<String , Object> userinfo = jwtService.parseToken(authorizationHeader.substring(7));
		String userid = (String) userinfo.get("credId");
		User temp = userservice.getUserById(userid);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(temp);
	}
	/**
     * edit issue by the issueid.
     *
     * @param issueid the issueid
     * @return the Issue
     */
    @PutMapping("/editissue/{issueid}")
	public	ResponseEntity<Issue> editIssue(@PathVariable String issueid,@RequestBody @Size(max = 400, message = "size cannot be mre than 400") String issuedescription){
		Issue  issue = userservice.editIssue(issueid, issuedescription);
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(issue);
    }
}