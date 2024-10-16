//package com.example.IssueDataMicroservice;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.example.IssueDataMicroservice.controller.FeedbackController;
//import com.example.IssueDataMicroservice.controller.IssueController;
//import com.example.IssueDataMicroservice.model.Feedback;
//import com.example.IssueDataMicroservice.model.Issue;
//import com.example.IssueDataMicroservice.service.IssueFeedbackService;
//
//@SpringBootTest
//public class controllertest {
//	  @Mock
//	    private IssueFeedbackService issueFeedbackService;
//
//	    @InjectMocks
//	    private IssueController issueController;
//
//	    @InjectMocks
//	    private FeedbackController feedbackController;
//
//
//	    @Test
//	    public void testAddIssue() throws Exception {
//	        Issue issue = new Issue(null, null, null, null, null, false, null, false, null);
//	        issue.setIssueid("1");
//	        when(issueFeedbackService.addissue(issue)).thenReturn(issue);
//
//	        ResponseEntity<Issue> response = issueController.addissue(issue);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	    }
//
//	    @Test
//	    public void testGetAllIssue() throws Exception {
//	        List<Issue> issues = new ArrayList<>();
//	        issues.add(new Issue(null, null, null, null, null, false, null, false, null));
//	        when(issueFeedbackService.getallissue("user123")).thenReturn(issues);
//
//	        ResponseEntity<List<Issue>> response = issueController.getallissue("user123");
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	    }
//	    @Test
//	    public void testGetByExpertidissue() throws Exception {
//	        String expertId = "expert123";
//	        List<Issue> issues = new ArrayList<>();
//	        issues.add(new Issue(expertId, expertId, expertId, expertId, expertId, false, expertId, false, expertId));
//	        when(issueFeedbackService.getByExpertidissue(expertId)).thenReturn(issues);
//
//	        ResponseEntity<List<Issue>> response = issueController.getByExpertidissue(expertId);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	    }
//
//	    @Test
//	    public void testGetByExpertidissueresolved() throws Exception {
//	        String expertId = "expert123";
//	        List<Issue> issues = new ArrayList<>();
//	        issues.add(new Issue(expertId, expertId, expertId, expertId, expertId, false, expertId, false, expertId));
//	        when(issueFeedbackService.getAllResolvedFeedbacksByexpertId(expertId)).thenReturn(issues);
//
//	        ResponseEntity<List<Issue>> response = issueController.getByExpertidissueresolved(expertId);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	    }
//
//	    @Test
//	    public void testGetByExpertidissueunresolved() throws Exception {
//	        String expertId = "expert123";
//	        List<Issue> issues = new ArrayList<>();
//	        issues.add(new Issue(expertId, expertId, expertId, expertId, expertId, false, expertId, false, expertId));
//	        when(issueFeedbackService.getAllUnResolvedFeedbacksByexpertId(expertId)).thenReturn(issues);
//
//	        ResponseEntity<List<Issue>> response = issueController.getByExpertidissueunresolved(expertId);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	    }
//	    @Test
//		public void testGetAllIssue2() throws Exception {
//		    // Prepare test data
//		    List<Issue> issues = new ArrayList<>();
//		    issues.add(new Issue(null, null, null, null, null, false, null, false, null));
//
//		    // Mock the service method to return the test data
//		    when(issueFeedbackService.getallissue()).thenReturn(issues);
//
//		    // Call the controller method
//		    ResponseEntity<List<Issue>> response = issueController.getallissue();
//
//		    // Verify the response status code
//		    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//		    // Verify the response body
//		    assertEquals(issues, response.getBody());
//		}
//
//	    @Test
//	    public void testGetAllFeedbacks() throws Exception {
//	        List<Feedback> feedbacks = new ArrayList<>();
//	        feedbacks.add(new Feedback(null, null, null, null, null));
//	        when(issueFeedbackService.getAllFeedbacks()).thenReturn(feedbacks);
//
//	        ResponseEntity<List<Feedback>> response = feedbackController.getAllFeedbacks();
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	        assertEquals(feedbacks, response.getBody());
//	    }
//	    
//	    @Test
//	    public void testGetFeedback() throws Exception {
//	        String feedbackId = "1";
//	        Feedback feedback = new Feedback(feedbackId, feedbackId, feedbackId, feedbackId, feedbackId);
//	        feedback.setFeedbackid(feedbackId);
//	        when(issueFeedbackService.getFeedback(feedbackId)).thenReturn(feedback);
//
//	        ResponseEntity<Feedback> response = feedbackController.getFeedback(feedbackId);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	        assertEquals(feedback, response.getBody());
//	    }
//	    @Test
//	    public void testGetAllFeedbacksByExpertId() throws Exception {
//	        String expertId = "1";
//	        List<Feedback> feedbacks = new ArrayList<>();
//	        feedbacks.add(new Feedback(expertId, expertId, expertId, expertId, expertId));
//	        when(issueFeedbackService.getAllFeedbacksByexpertId(expertId)).thenReturn(feedbacks);
//
//	        ResponseEntity<List<Feedback>> response = feedbackController.getAllFeedbacksByexpertId(expertId);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	        assertEquals(feedbacks, response.getBody());
//	    }
//	    
//	    @Test
//	    public void testGetFeedbacksByFeedbackId() throws Exception {
//	        String feedbackId = "1";
//	        Feedback feedback = new Feedback(feedbackId, feedbackId, feedbackId, feedbackId, feedbackId);
//	        feedback.setFeedbackid(feedbackId);
//	        when(issueFeedbackService.getAllFeedbacksByFeedbackid(feedbackId)).thenReturn(feedback);
//
//	        ResponseEntity<Feedback> response = feedbackController.getAllFeedbacksByFeedbackid(feedbackId);
//
//	        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//	        assertEquals(feedback, response.getBody());
//	    }
//
//}
