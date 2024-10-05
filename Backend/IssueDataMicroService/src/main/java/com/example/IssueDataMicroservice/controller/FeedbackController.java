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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IssueDataMicroservice.exception.FeedbackNotFoundException;
import com.example.IssueDataMicroservice.model.Feedback;
import com.example.IssueDataMicroservice.service.IssueFeedbackService;

import io.swagger.v3.oas.annotations.Operation;

// TODO: Auto-generated Javadoc
/**
 * The Class FeedbackController.
 */
@RestController
@RequestMapping("/feedback")
//@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {
	
	/** The issue feedback service. */
	@Autowired
	IssueFeedbackService issueFeedbackService;
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	/**
	 * Gets the all feedbacks.
	 *
	 * @return the all feedbacks
	 * @throws Exception the exception
	 */
	//admin
	@GetMapping("/all")
	@Operation(summary = "get all feedbacks in the database to be in used by user and expert microservice")
	public	ResponseEntity<List<Feedback>> getAllFeedbacks()  throws Exception{
		List<Feedback> feedbacks = issueFeedbackService.getAllFeedbacks();
		if(feedbacks.isEmpty() || feedbacks==null) {
			logger.error("Feedbacks not fetched");
		}else {
			logger.info("Feedbacks fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedbacks);
	}
	
	/**
	 * Gets the feedback.
	 *
	 * @param feedbackid the feedbackid
	 * @return the feedback
	 * @throws Exception the exception
	 */
	@GetMapping("/{feedbackid}")
	@Operation(summary = "get particular feedback from the database to be in used by user and expert microservice")
	public	ResponseEntity<Feedback> getFeedback(@PathVariable String feedbackid)  throws Exception{
		Feedback feedback = issueFeedbackService.getFeedback(feedbackid);
		if(feedback==null) {
			throw new FeedbackNotFoundException("Feedback with feedbackId "+feedbackid+" not found");
		}else {
			logger.info("Feedback fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedback);
	}
	
	/**
	 * Gets the all feedbacks byexpert id.
	 *
	 * @param expertid the expertid
	 * @return the all feedbacks byexpert id
	 * @throws Exception the exception
	 */
	//expert
	@GetMapping("/byexpertid/{expertid}")
	@Operation(summary = "get all feedbacks by expert id in the database to be in used by user and expert microservice")
	public	ResponseEntity<List<Feedback>> getAllFeedbacksByexpertId(@PathVariable String expertid) throws Exception{
		List<Feedback> feedbacks = issueFeedbackService.getAllFeedbacksByexpertId(expertid);
		if(feedbacks.isEmpty() || feedbacks==null) {
			logger.error("Feedbacks not fetched");
		}else {
			logger.info("Feedbacks fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedbacks);
	}
	
	/**
	 * Gets the all feedbacks by feedbackid.
	 *
	 * @param feedbackid the feedbackid
	 * @return the all feedbacks by feedbackid
	 * @throws Exception the exception
	 */
	
	
	@GetMapping("/byfeedbackid/{feedbackid}")
	@Operation(summary = "get feedback by feedbackid in the database to be in used by user and expert microservice")
	public	ResponseEntity<Feedback> getAllFeedbacksByFeedbackid(@PathVariable String feedbackid) throws Exception{
		Feedback feedback = issueFeedbackService.getAllFeedbacksByFeedbackid(feedbackid);
		if(feedback==null) {
			logger.error("Feedback not fetched");
		}else {
			logger.info("Feedback fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedback);
	}
	
	/**
	 * Editfeedback.
	 *
	 * @param feedbackid the feedbackid
	 * @param feedbackDescription the feedback description
	 * @return the response entity
	 * @throws Exception the exception
	 */
	//for expert
	@PutMapping("/edit/{feedbackid}/{feedbackDescription}")
	@Operation(summary = "edit feedbacks in the database to be in used by user and expert microservice")
	public	ResponseEntity<Feedback> editfeedback(@PathVariable String feedbackid, @PathVariable String feedbackDescription) throws Exception {
		Feedback feedback = issueFeedbackService.editfeedback(feedbackid, feedbackDescription);
		if(feedback==null) {
			logger.error("Feedback not fetched");
		}else {
			logger.info("Feedback fetched");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedback);
	} 
}     