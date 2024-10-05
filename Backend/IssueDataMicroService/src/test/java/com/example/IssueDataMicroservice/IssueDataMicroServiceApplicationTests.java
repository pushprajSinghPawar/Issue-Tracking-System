package com.example.IssueDataMicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.IssueDataMicroservice.exception.ExceptionResponse;
import com.example.IssueDataMicroservice.exception.GlobalExceptionHandler;
import com.example.IssueDataMicroservice.externalService.ExpertMicroServiceClient;
import com.example.IssueDataMicroservice.model.Expert;
import com.example.IssueDataMicroservice.model.Feedback;
import com.example.IssueDataMicroservice.model.Issue;
import com.example.IssueDataMicroservice.repository.FeedbackRepository;
import com.example.IssueDataMicroservice.repository.IssueRepository;
import com.example.IssueDataMicroservice.service.IssueFeedbackServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class IssueDataMicroServiceApplicationTests.
 */
@SpringBootTest
class IssueDataMicroServiceApplicationTests {

	/** The issue repository. */
	@Mock
	private IssueRepository issueRepository;

	/** The feedback repository. */
	@Mock
	private FeedbackRepository feedbackRepository;

	/** The expert micro service client. */
	@Mock
	private ExpertMicroServiceClient expertMicroServiceClient;

	/** The issue feedback service. */
	@InjectMocks
	private IssueFeedbackServiceImpl issueFeedbackService;

	/**
	 * Setup.
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test expert.
	 */
	@Test
	public void testExpert() {
		String expertId = "1";
		String name = "John Doe";
		String password = "password";
		List<String> specialistIn = Arrays.asList("Java", "Spring");
		List<String> issues = Arrays.asList("Issue 1", "Issue 2");
		List<String> feedbacks = Arrays.asList("Feedback 1", "Feedback 2");

		com.example.IssueDataMicroservice.model.Expert expert = new Expert(expertId, name, password, specialistIn,
				issues, feedbacks);

		assertEquals(expertId, expert.getExpertid());
		assertEquals(name, expert.getName());
		assertEquals(password, expert.getPassword());
		assertEquals(specialistIn, expert.getSpecialistIn());
		assertEquals(issues, expert.getIssues());
		assertEquals(feedbacks, expert.getFeedbacks());
	}

	/**
	 * Test setters expert.
	 */
	@Test
	public void testSettersExpert() {
		String expertId = "1";
		String name = "John Doe";
		String password = "password";
		List<String> specialistIn = Arrays.asList("Java", "Spring");
		List<String> issues = Arrays.asList("Issue 1", "Issue 2");
		List<String> feedbacks = Arrays.asList("Feedback 1", "Feedback 2");

		Expert expert = new Expert("", "", "", Arrays.asList(), Arrays.asList(), Arrays.asList());

		expert.setExpertid(expertId);
		expert.setName(name);
		expert.setPassword(password);
		expert.setSpecialistIn(specialistIn);
		expert.setIssues(issues);
		expert.setFeedbacks(feedbacks);

		assertEquals(expertId, expert.getExpertid());
		assertEquals(name, expert.getName());
		assertEquals(password, expert.getPassword());
		assertEquals(specialistIn, expert.getSpecialistIn());
		assertEquals(issues, expert.getIssues());
		assertEquals(feedbacks, expert.getFeedbacks());
	}

	/**
	 * Test feedback.
	 */
	@Test
	public void testFeedback() {
		String feedbackId = "1";
		String userId = "user1";
		String issueId = "issue1";
		String expertId = "expert1";
		String feedbackDescription = "This is a feedback description";
		Date dateFeedbackGiven = new Date();

		Feedback feedback = new Feedback(feedbackId, userId, issueId, expertId, feedbackDescription);
		feedback.setFeedbackgiven(dateFeedbackGiven);

		assertEquals(feedbackId, feedback.getFeedbackid());
		assertEquals(userId, feedback.getUserid());
		assertEquals(issueId, feedback.getIssueid());
		assertEquals(expertId, feedback.getExpertid());
		assertEquals(feedbackDescription, feedback.getFeedbackDescription());
		assertEquals(dateFeedbackGiven, feedback.getFeedbackgiven());
	}

	/**
	 * Test setters feedback.
	 */
	@Test
	public void testSettersFeedback() {
		String feedbackId = "1";
		String userId = "user1";
		String issueId = "issue1";
		String expertId = "expert1";
		String feedbackDescription = "This is a feedback description";
		Date dateFeedbackGiven = new Date();

		Feedback feedback = new Feedback("", "", "", "", "");

		feedback.setFeedbackid(feedbackId);
		feedback.setUserid(userId);
		feedback.setIssueid(issueId);
		feedback.setExpertid(expertId);
		feedback.setFeedbackDescription(feedbackDescription);
		feedback.setFeedbackgiven(dateFeedbackGiven);

		assertEquals(feedbackId, feedback.getFeedbackid());
		assertEquals(userId, feedback.getUserid());
		assertEquals(issueId, feedback.getIssueid());
		assertEquals(expertId, feedback.getExpertid());
		assertEquals(feedbackDescription, feedback.getFeedbackDescription());
		assertEquals(dateFeedbackGiven, feedback.getFeedbackgiven());
		assertTrue(feedback.toString().length() > 0);
	}

	/**
	 * Test issue.
	 */
	@Test
	public void testIssue() {
		String issueId = "1";
		String softwareName = "Software";
		String softwareIssueType = "Type";
		String softwareIssueTitle = "Title";
		String softwareIssueDescription = "Description";
		boolean highPriority = true;
		String status = "Open";
		boolean paidTicket = false;
		String userId = "user1";

		Issue issue = new Issue(issueId, softwareName, softwareIssueType, softwareIssueTitle, softwareIssueDescription,
				highPriority, status, paidTicket, userId);

		assertEquals(issueId, issue.getIssueid());
		assertEquals(softwareName, issue.getSoftwareName());
		assertEquals(softwareIssueType, issue.getSoftwareIssueType());
		assertEquals(softwareIssueTitle, issue.getSoftwareIssueTitle());
		assertEquals(softwareIssueDescription, issue.getSoftwareIssueDescription());
		assertEquals(highPriority, issue.isHighPriority());
		assertEquals(status, issue.getStatus());
		assertEquals(paidTicket, issue.isPaidTicket());
		assertEquals(userId, issue.getUserid());
		assertEquals(new Date().toString(), issue.getDateissueform().toString());
	}

	/**
	 * Test setters issue.
	 */
	@Test
	public void testSettersIssue() {
		String issueId = "1";
		String softwareName = "Software";
		String softwareIssueType = "Type";
		String softwareIssueTitle = "Title";
		String softwareIssueDescription = "Description";
		boolean highPriority = true;
		String status = "Open";
		boolean paidTicket = false;
		String userId = "user1";

		Issue issue = new Issue("", "", "", "", "", false, "", false, "");

		issue.setIssueid(issueId);
		issue.setSoftwareName(softwareName);
		issue.setSoftwareIssueType(softwareIssueType);
		issue.setSoftwareIssueTitle(softwareIssueTitle);
		issue.setSoftwareIssueDescription(softwareIssueDescription);
		issue.setHighPriority(highPriority);
		issue.setStatus(status);
		issue.setPaidTicket(paidTicket);
		issue.setUserid(userId);
		issue.setDateissueform(new Date());

		assertEquals(issueId, issue.getIssueid());
		assertEquals(softwareName, issue.getSoftwareName());
		assertEquals(softwareIssueType, issue.getSoftwareIssueType());
		assertEquals(softwareIssueTitle, issue.getSoftwareIssueTitle());
		assertEquals(softwareIssueDescription, issue.getSoftwareIssueDescription());
		assertEquals(highPriority, issue.isHighPriority());
		assertEquals(status, issue.getStatus());
		assertEquals(paidTicket, issue.isPaidTicket());
		assertEquals(userId, issue.getUserid());
	}

	/**
	 * Test add issue.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAddIssue() throws Exception {
		Issue issue = new Issue("issueId", "softwareName", "issueType", "issueTitle", "issueDescription", true,
				"status", true, "userId");
		when(feedbackRepository.save(any(Feedback.class)))
				.thenReturn(new Feedback("fbissueId", "userId", "issueId", "expertId", ""));
		when(issueRepository.save(any(Issue.class))).thenReturn(issue);

		Issue result = issueFeedbackService.addissue(issue);

		assertNotNull(result);
		assertEquals(issue, result);
	}

	/**
	 * Test get all issues by user id.
	 */
	@Test
	public void testGetAllIssuesByUserId() {
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("issueId", "softwareName", "issueType", "issueTitle", "issueDescription", false, "status",
				false, "userId"));
		when(issueRepository.findByUserid("userId")).thenReturn(issues);

		List<Issue> result = issueFeedbackService.getallissue("userId");

		assertNotNull(result);
		assertEquals(issues, result);
	}

	/**
	 * Test get by expertidissue.
	 */
	@Test
	public void testGetByExpertidissue() {
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("issueId", "softwareName", "issueType", "issueTitle", "issueDescription", false, "status",
				false, "userId"));
		when(issueRepository.findAll()).thenReturn(issues);

		List<Issue> result = issueFeedbackService.getByExpertidissue("expertId");

		assertNotNull(result);
		assertEquals(
				issues.stream().filter(issue -> issue.getIssueid().startsWith("expertId")).collect(Collectors.toList()),
				result);

	}

	/**
	 * Test get all issue.
	 */
	@Test
	public void testGetAllIssue() {
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("issueId", "softwareName", "issueType", "issueTitle", "issueDescription", false, "status",
				false, "userId"));
		when(issueRepository.findAll()).thenReturn(issues);

		List<Issue> result = issueFeedbackService.getallissue();

		assertNotNull(result);
		assertEquals(issues, result);

	}

	/**
	 * Test get feedback.
	 */
	@Test
	public void testGetFeedback() {
		String feedbackId = "feedbackId";
		when(feedbackRepository.findById(feedbackId)).thenReturn(
				Optional.of(new Feedback("feedbackId", "userId", "issueId", "expertId", "feedbackDescription")));

		Feedback result = issueFeedbackService.getFeedback(feedbackId);

		assertNotNull(result);
		assertEquals("feedbackId", result.getFeedbackid());
		assertEquals("userId", result.getUserid());
		assertEquals("issueId", result.getIssueid());
		assertEquals("expertId", result.getExpertid());
		assertEquals("feedbackDescription", result.getFeedbackDescription());
	}

	/**
	 * Test get all resolved feedbacks by expert id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllResolvedFeedbacksByExpertId() throws Exception {
		// Creating a list of issues
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("expertId1-issueId1", "softwareName1", "issueType1", "issueTitle1", "issueDescription1",
				false, "resolved", false, "expertId1"));
		issues.add(new Issue("expertId1-issueId2", "softwareName2", "issueType2", "issueTitle2", "issueDescription2",
				false, "unresolved", false, "expertId1"));
		when(issueRepository.findAll()).thenReturn(issues);

		// Creating an instance of IssueFeedbackService

		// Calling the method to test
		List<Issue> result = issueFeedbackService.getAllResolvedFeedbacksByexpertId("expertId1");

		// Verifying the result
		assertEquals(1, result.size());
	}

	/**
	 * Test get all un resolved feedbacks by expert id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllUnResolvedFeedbacksByExpertId() throws Exception {
		// Creating a list of issues
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("expertId1-issueId1", "softwareName1", "issueType1", "issueTitle1", "issueDescription1",
				false, "unresolved", false, "expertId1"));
		issues.add(new Issue("expertId1-issueId2", "softwareName2", "issueType2", "issueTitle2", "issueDescription2",
				false, "unresolved", false, "expertId1"));
		when(issueRepository.findAll()).thenReturn(issues);

		// Creating an instance of IssueFeedbackService

		// Calling the method to test
		List<Issue> result = issueFeedbackService.getAllUnResolvedFeedbacksByexpertId("expertId1");

		// Verifying the result
		assertEquals(2, result.size());
	}

	/**
	 * Test get all feedbacks.
	 */
	@Test
	public void testGetAllFeedbacks() {
		List<Feedback> feedbacks = new ArrayList<>();
		feedbacks.add(new Feedback("feedbackId1", "userId1", "issueId1", "expertId1", "description1"));
		feedbacks.add(new Feedback("feedbackId2", "userId2", "issueId2", "expertId1", "description2"));
		when(feedbackRepository.findAll()).thenReturn(feedbacks);

		// Calling the method to test
		List<Feedback> result = issueFeedbackService.getAllFeedbacks();

		// Verifying the result
		assertEquals(2, result.size());
	}

	/**
	 * Test get all feedbacks by expert id.
	 */
	@Test
	public void testGetAllFeedbacksByExpertId() {

		// Creating a list of feedbacks
		List<Feedback> feedbacks = new ArrayList<>();
		feedbacks.add(new Feedback("feedbackId1", "userId1", "issueId1", "expertId1", "description1"));
		feedbacks.add(new Feedback("feedbackId2", "userId2", "issueId2", "expertId1", "description2"));
		when(feedbackRepository.findByExpertid("expertId1")).thenReturn(feedbacks);

		// Calling the method to test
		List<Feedback> result = issueFeedbackService.getAllFeedbacksByexpertId("expertId1");

		// Verifying the result
		assertEquals(2, result.size());
	}

	/**
	 * Test get all feedbacks by feedback id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllFeedbacksByFeedbackId() throws Exception {
		// Creating a feedback
		Feedback feedback = new Feedback("feedbackId", "userId", "issueId", "expertId", "description");
		when(feedbackRepository.findById("feedbackId")).thenReturn(Optional.of(feedback));

		// Calling the method to test
		Feedback result = issueFeedbackService.getAllFeedbacksByFeedbackid("feedbackId");

		assertNotNull(result);
	}

	/**
	 * Test edit feedback.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testEditFeedback() throws Exception {
		// Mock data
		String feedbackDescription = "New feedback description";

		Feedback feedback = new Feedback("fbissueId", "userId", "issueId", "expertId", "Old description");
		Issue issue = new Issue("issueId", "softwareName", "issueType", "issueTitle", "issueDescription", true,
				"status", true, "userId");

		// Mocking the repositories
		when(feedbackRepository.findById("fbissueId")).thenReturn(Optional.of(feedback));
		when(issueRepository.findByIssueid("issueId")).thenReturn(issue);
		when(feedbackRepository.save(feedback)).thenReturn(feedback);

		System.out.println(issueRepository.findByIssueid("issueId"));
		Feedback rt = issueFeedbackService.editfeedback("fbissueId", feedbackDescription);

		assertEquals(feedbackDescription, rt.getFeedbackDescription());
	}
	
	/**
	 * Test get all resolved feedbacks by expert id invalid.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllResolvedFeedbacksByExpertIdInvalid() throws Exception {
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("expertId1-issueId1", "softwareName1", "issueType1", "issueTitle1", "issueDescription1",
				false, "resolved", false, "expertId1"));
		issues.add(new Issue("expertId1-issueId2", "softwareName2", "issueType2", "issueTitle2", "issueDescription2",
				false, "unresolved", false, "expertId1"));
		when(issueRepository.findAll()).thenReturn(issues);
		// Verifying the result
		assertThrows(Exception.class, ()->issueFeedbackService.getAllResolvedFeedbacksByexpertId("expertId9"));
	}
	
	/**
	 * Test get all un resolved feedbacks by expert id invalid.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllUnResolvedFeedbacksByExpertIdInvalid() throws Exception {
		List<Issue> issues = new ArrayList<>();
		issues.add(new Issue("expertId1-issueId1", "softwareName1", "issueType1", "issueTitle1", "issueDescription1",
				false, "resolved", false, "expertId1"));
		issues.add(new Issue("expertId1-issueId2", "softwareName2", "issueType2", "issueTitle2", "issueDescription2",
				false, "unresolved", false, "expertId1"));
		when(issueRepository.findAll()).thenReturn(issues);
		// Verifying the result
		assertThrows(Exception.class, ()->issueFeedbackService.getAllUnResolvedFeedbacksByexpertId("expertId9"));
	}
	 
 	/**
 	 * Test method argument not valid exception.
 	 */
 	@Test
	    public void testMethodArgumentNotValidException() {
	        GlobalExceptionHandler handler = new GlobalExceptionHandler();
	        BindingResult bindingResult = new BindException(new Object(), "object");
	        bindingResult.addError(new FieldError("object", "field", "Validation failed"));
	        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
	        ResponseEntity<ExceptionResponse> responseEntity = handler.getresponse(exception);

	        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	        assertEquals("Validation failed", responseEntity.getBody().getMessage());
	    }


	    /**
    	 * Test all exceptions.
    	 */
    	@Test
	    public void testAllExceptions() {
	        GlobalExceptionHandler handler = new GlobalExceptionHandler();
	        Exception exception = new Exception("Internal Server Error");
	        ResponseEntity<ExceptionResponse> responseEntity = handler.allExceptions(exception);

	        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	        assertEquals("Internal Server Error", responseEntity.getBody().getMessage());
	    }    
	    
    

}
