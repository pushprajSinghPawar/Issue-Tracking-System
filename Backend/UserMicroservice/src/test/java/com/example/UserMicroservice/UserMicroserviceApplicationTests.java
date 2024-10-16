//package com.example.UserMicroservice;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import com.example.UserMicroservice.exception.ExceptionResponse;
//import com.example.UserMicroservice.exception.ExpertNotFound;
//import com.example.UserMicroservice.exception.GlobalExceptionHandler;
//import com.example.UserMicroservice.externalServices.ExpertMicroServicesClient;
//import com.example.UserMicroservice.externalServices.IssueMicroServiceClient;
//import com.example.UserMicroservice.externalServices.SecurtiyMicroserviceClient;
//import com.example.UserMicroservice.model.Expert;
//import com.example.UserMicroservice.model.Feedback;
//import com.example.UserMicroservice.model.Issue;
//import com.example.UserMicroservice.model.PaymentInfo;
//import com.example.UserMicroservice.model.User;
//import com.example.UserMicroservice.model.credentials;
//import com.example.UserMicroservice.repository.AuthRepo;
//import com.example.UserMicroservice.repository.UserRepository;
//import com.example.UserMicroservice.service.JwtService;
//import com.example.UserMicroservice.service.UserServiceImpl;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class UserMicroserviceApplicationTests.
// */
//@SpringBootTest
//class UserMicroserviceApplicationTests {
//	
//	/** The user repository. */
//	@Mock
//    private UserRepository userRepository;
//
//    /** The security microservice client. */
//    @Mock
//    private SecurtiyMicroserviceClient securityMicroserviceClient;
//
//    /** The issue micro service client. */
//    @Mock
//    private IssueMicroServiceClient issueMicroServiceClient;
//
//    /** The expert micro services client. */
//    @Mock
//    private ExpertMicroServicesClient expertMicroServicesClient;
//
//    /** The auth repo. */
//    @Mock
//    private AuthRepo authRepo;
//
//    /** The user service. */
//    @InjectMocks
//    private UserServiceImpl userService;
//    
//    /** The jwt service. */
//    @InjectMocks
//    private JwtService jwtService;
//    
//    @InjectMocks
//    PaymentInfo paymentInfo;
//
//    @Test
//    public void testGetSetUserId() {
//        String userId = "user123";
//        paymentInfo.setUserid(userId);
//        assertEquals(userId, paymentInfo.getUserid());
//    }
//
//    @Test
//    public void testGetSetTransactionId() {
//        String transactionId = "trans123";
//        paymentInfo.setTransactionId(transactionId);
//        assertEquals(transactionId, paymentInfo.getTransactionId());
//    }
//
//    @Test
//    public void testConstructor() {
//        String userId = "user123";
//        String transactionId = "trans123";
//        PaymentInfo newPaymentInfo = new PaymentInfo(userId, transactionId);
//        assertEquals(userId, newPaymentInfo.getUserid());
//        assertEquals(transactionId, newPaymentInfo.getTransactionId());
//    }
//
// 
//    /**
//     * Test credentials.
//     */
//    @Test
//    public void testCredentials() {
//        String credentialsId = "123";
//        String username = "user";
//        String password = "password";
//        String role = "role";
//
//        credentials credentials = new credentials(credentialsId, username, password, role);
//
//        assertEquals(credentialsId, credentials.getCredentialsId());
//        assertEquals(username, credentials.getUsername());
//        assertEquals(password, credentials.getPassword());
//        assertEquals(role, credentials.getRole());
//    }
//
//    /**
//     * Test setters.
//     */
//    @Test
//    public void testSetters() {
//        String credentialsId = "123";
//        String username = "user";
//        String password = "password";
//        String role = "role";
//
//        credentials credentials = new credentials("", "", "", "");
//
//        credentials.setCredentialsId(credentialsId);
//        credentials.setUsername(username);
//        credentials.setPassword(password);
//        credentials.setRole(role);
//
//        assertEquals(credentialsId, credentials.getCredentialsId());
//        assertEquals(username, credentials.getUsername());
//        assertEquals(password, credentials.getPassword());
//        assertEquals(role, credentials.getRole());
//    }
//    
//    /**
//     * Test expert.
//     */
//    @Test
//    public void testExpert() {
//        String expertId = "1";
//        String name = "John Doe";
//        String password = "password";
//        List<String> specialistIn = Arrays.asList("Java", "Spring");
//        List<String> issues = Arrays.asList("Issue 1", "Issue 2");
//        List<String> feedbacks = Arrays.asList("Feedback 1", "Feedback 2");
//
//        Expert expert = new Expert(expertId, name, password, specialistIn, issues, feedbacks);
//
//        assertEquals(expertId, expert.getExpertid());
//        assertEquals(name, expert.getName());
//        assertEquals(password, expert.getPassword());
//        assertEquals(specialistIn, expert.getSpecialistIn());
//        assertEquals(issues, expert.getIssues());
//        assertEquals(feedbacks, expert.getFeedbacks());
//    }
//
//    /**
//     * Test setters expert.
//     */
//    @Test
//    public void testSettersExpert() {
//        String expertId = "1";
//        String name = "John Doe";
//        String password = "password";
//        List<String> specialistIn = Arrays.asList("Java", "Spring");
//        List<String> issues = Arrays.asList("Issue 1", "Issue 2");
//        List<String> feedbacks = Arrays.asList("Feedback 1", "Feedback 2");
//
//        Expert expert = new Expert("", "", "", Arrays.asList(), Arrays.asList(), Arrays.asList());
//
//        expert.setExpertid(expertId);
//        expert.setName(name);
//        expert.setPassword(password);
//        expert.setSpecialistIn(specialistIn);
//        expert.setIssues(issues);
//        expert.setFeedbacks(feedbacks);
//
//        assertEquals(expertId, expert.getExpertid());
//        assertEquals(name, expert.getName());
//        assertEquals(password, expert.getPassword());
//        assertEquals(specialistIn, expert.getSpecialistIn());
//        assertEquals(issues, expert.getIssues());
//        assertEquals(feedbacks, expert.getFeedbacks());
//    }
//
//    /**
//     * Test feedback.
//     */
//    @Test
//    public void testFeedback() {
//        String feedbackId = "1";
//        String userId = "user1";
//        String issueId = "issue1";
//        String expertId = "expert1";
//        String feedbackDescription = "This is a feedback description";
//        Date dateFeedbackGiven = new Date();
//
//        Feedback feedback = new Feedback(feedbackId, userId, issueId, expertId, feedbackDescription);
//        feedback.setFeedbackgiven(dateFeedbackGiven);
//
//        assertEquals(feedbackId, feedback.getFeedbackid());
//        assertEquals(userId, feedback.getUserid());
//        assertEquals(issueId, feedback.getIssueid());
//        assertEquals(expertId, feedback.getExpertid());
//        assertEquals(feedbackDescription, feedback.getFeedbackDescription());
//        assertEquals(dateFeedbackGiven, feedback.getFeedbackgiven());
//    }
//
//    /**
//     * Test setters feedback.
//     */
//    @Test
//    public void testSettersFeedback() {
//        String feedbackId = "1";
//        String userId = "user1";
//        String issueId = "issue1";
//        String expertId = "expert1";
//        String feedbackDescription = "This is a feedback description";
//        Date dateFeedbackGiven = new Date();
//
//        Feedback feedback = new Feedback("", "", "", "", "");
//
//        feedback.setFeedbackid(feedbackId);
//        feedback.setUserid(userId);
//        feedback.setIssueid(issueId);
//        feedback.setExpertid(expertId);
//        feedback.setFeedbackDescription(feedbackDescription);
//        feedback.setFeedbackgiven(dateFeedbackGiven);
//
//        assertEquals(feedbackId, feedback.getFeedbackid());
//        assertEquals(userId, feedback.getUserid());
//        assertEquals(issueId, feedback.getIssueid());
//        assertEquals(expertId, feedback.getExpertid());
//        assertEquals(feedbackDescription, feedback.getFeedbackDescription());
//        assertEquals(dateFeedbackGiven, feedback.getFeedbackgiven());
//    }
//    
//
//    /**
//     * Test issue.
//     */
//    @Test
//    public void testIssue() {
//        String issueId = "1";
//        String softwareName = "Software";
//        String softwareIssueType = "Type";
//        String softwareIssueTitle = "Title";
//        String softwareIssueDescription = "Description";
//        boolean highPriority = true;
//        String status = "Open";
//        boolean paidTicket = false;
//        String userId = "user1";
//
//        Issue issue = new Issue(issueId, softwareName, softwareIssueType, softwareIssueTitle, softwareIssueDescription, highPriority, status, paidTicket, userId);
//
//        assertEquals(issueId, issue.getIssueid());
//        assertEquals(softwareName, issue.getSoftwareName());
//        assertEquals(softwareIssueType, issue.getSoftwareIssueType());
//        assertEquals(softwareIssueTitle, issue.getSoftwareIssueTitle());
//        assertEquals(softwareIssueDescription, issue.getSoftwareIssueDescription());
//        assertEquals(highPriority, issue.isHighPriority());
//        assertEquals(status, issue.getStatus());
//        assertEquals(paidTicket, issue.isPaidTicket());
//        assertEquals(userId, issue.getUserid());
//        assertEquals(new Date().toString(), issue.getDateissueform().toString());
//
//        String expectedToString = "Issue [issueid=1, softwareName=Software, softwareIssueType=Type, softwareIssueTitle=Title, softwareIssueDescription=Description, highPriority=true, status=Open, paidTicket=false, userid=user1, dateissueform=" + new Date() + "]";
//        assertEquals(expectedToString, issue.toString());
//    }
//
//    /**
//     * Test setters issue.
//     */
//    @Test
//    public void testSettersIssue() {
//        String issueId = "1";
//        String softwareName = "Software";
//        String softwareIssueType = "Type";
//        String softwareIssueTitle = "Title";
//        String softwareIssueDescription = "Description";
//        boolean highPriority = true;
//        String status = "Open";
//        boolean paidTicket = false;
//        String userId = "user1";
//
//        Issue issue = new Issue("", "", "", "", "", false, "", false, "");
//
//        issue.setIssueid(issueId);
//        issue.setSoftwareName(softwareName);
//        issue.setSoftwareIssueType(softwareIssueType);
//        issue.setSoftwareIssueTitle(softwareIssueTitle);
//        issue.setSoftwareIssueDescription(softwareIssueDescription);
//        issue.setHighPriority(highPriority);
//        issue.setStatus(status);
//        issue.setPaidTicket(paidTicket);
//        issue.setUserid(userId);
//
//        assertEquals(issueId, issue.getIssueid());
//        assertEquals(softwareName, issue.getSoftwareName());
//        assertEquals(softwareIssueType, issue.getSoftwareIssueType());
//        assertEquals(softwareIssueTitle, issue.getSoftwareIssueTitle());
//        assertEquals(softwareIssueDescription, issue.getSoftwareIssueDescription());
//        assertEquals(highPriority, issue.isHighPriority());
//        assertEquals(status, issue.getStatus());
//        assertEquals(paidTicket, issue.isPaidTicket());
//        assertEquals(userId, issue.getUserid());
//    }
//    
//    /**
//     * Test user.
//     */
//    @Test
//    public void testUser() {
//        String userId = "1";
//        String name = "John Doe";
//        String position = "Developer";
//        String companyName = "Example Company";
//        String password = "password";
//        List<String> issues = Arrays.asList("Issue 1", "Issue 2");
//        List<String> feedbacks = Arrays.asList("Feedback 1", "Feedback 2");
//
//        User user = new User(userId, name, position, companyName, password, issues, feedbacks);
//
//        assertEquals(userId, user.getUserid());
//        assertEquals(name, user.getName());
//        assertEquals(position, user.getPosition());
//        assertEquals(companyName, user.getCompanyName());
//        assertEquals(password, user.getPassword());
//        assertEquals(issues, user.getIssues());
//        assertEquals(feedbacks, user.getFeedBacks());
//        assertEquals(Arrays.asList("User"), user.getRoles());
//    }
//
//    /**
//     * Test setters user.
//     */
//    @Test
//    public void testSettersUser() {
//        String userId = "1";
//        String name = "John Doe";
//        String position = "Developer";
//        String companyName = "Example Company";
//        String password = "password";
//        List<String> issues = Arrays.asList("Issue 1", "Issue 2");
//        List<String> feedbacks = Arrays.asList("Feedback 1", "Feedback 2");
//
//        User user = new User("", "", "", "", "", Arrays.asList(), Arrays.asList());
//
//        user.setUserid(userId);
//        user.setName(name);
//        user.setPosition(position);
//        user.setCompanyName(companyName);
//        user.setPassword(password);
//        user.setIssues(issues);
//        user.setFeedBacks(feedbacks);
//        user.setRoles(Arrays.asList("User"));
//
//        assertEquals(userId, user.getUserid());
//        assertEquals(name, user.getName());
//        assertEquals(position, user.getPosition());
//        assertEquals(companyName, user.getCompanyName());
//        assertEquals(password, user.getPassword());
//        assertEquals(issues, user.getIssues());
//        assertEquals(feedbacks, user.getFeedBacks());
//        assertEquals(Arrays.asList("User"), user.getRoles());
//    }
//    
//    /**
//     * Test add user.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void testAddUser() throws Exception {
//        User user = new User("1", "John Doe", "Developer", "Example Company", "password", Arrays.asList(), Arrays.asList());
//        when(userRepository.findById("1")).thenReturn(java.util.Optional.empty());
//        when(userRepository.save(user)).thenReturn(user);
//
//        User savedUser = userService.addUser(user);
//
//        assertEquals("1", savedUser.getUserid());
//        assertEquals("John Doe", savedUser.getName());
//        assertEquals("Developer", savedUser.getPosition());
//        assertEquals("Example Company", savedUser.getCompanyName());
//        assertEquals("password", savedUser.getPassword());
//        assertEquals(Arrays.asList("user"), savedUser.getRoles());
//    }
//
//    /**
//     * Test add user user already exists.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void testAddUser_UserAlreadyExists() throws Exception {
//        User user = new User("1", "John Doe", "Developer", "Example Company", "password", Arrays.asList(), Arrays.asList());
//        when(userRepository.findById("1")).thenReturn(java.util.Optional.of(user));
//
//        assertThrows(Exception.class, ()-> userService.addUser(user));
//    }
//
//    /**
//     * Test add issue.
//     *
//     * @throws ExpertNotFound the expert not found
//     */
//    @Test
//    public void testAddIssue() throws ExpertNotFound {
//        Issue issue = new Issue("1", "Software", "Bug", "Title", "Description", true, "Open", false, "user1");
//        when(expertMicroServicesClient.addissue(issue)).thenReturn(true);
//
//        String result = userService.addIssue(issue, "user1");
//
//        assertEquals("Succesfully Submitted Issue ", result);
//    }
//    
//    /**
//     * Test add issue invalidissue.
//     */
//    @Test
//    public void testAddIssueInvalidissue() {
//        Issue issue = new Issue("1", "Software", "Bug", "Title", "Description", true,null, false, "user1");
//        when(expertMicroServicesClient.addissue(issue)).thenReturn(false);
//
//        assertThrows(ExpertNotFound.class,()-> userService.addIssue(issue, "user1"));
//    }
//    
//
//    /**
//     * Test view resolved issues.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void testViewResolvedIssues() throws Exception {
//        String userId = "user1";
//        List<Issue> allIssues = Arrays.asList(
//            new Issue("1", "Software", "Bug", "Title", "Description", true, "Resolved", false, userId),
//            new Issue("2", "Software", "Feature Request", "Title", "Description", false, "Open", false, userId)
//        );
//        when(issueMicroServiceClient.getallissue(userId)).thenReturn(allIssues);
//
//        List<Issue> resolvedIssues = userService.viewResolvedissue(userId);
//
//        assertEquals(1, resolvedIssues.size());
//        assertEquals("1", resolvedIssues.get(0).getIssueid());
//    }
//
//    /**
//     * Test view un resolved issues.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void testViewUnResolvedIssues() throws Exception {
//        String userId = "user1";
//        List<Issue> allIssues = Arrays.asList(
//            new Issue("1", "Software", "Bug", "Title", "Description", true, "resolved", false, userId),
//            new Issue("2", "Software", "Feature Request", "Title", "Description", false, "unresolved", false, userId)
//        );
//        when(issueMicroServiceClient.getallissue(userId)).thenReturn(allIssues);
//
//        List<Issue> unresolvedIssues = userService.viewUnResolvedissue(userId);
//
//        assertEquals(1, unresolvedIssues.size());
//        assertEquals("2", unresolvedIssues.get(0).getIssueid());
//    }
//
//    /**
//     * Test view feedback by id.
//     */
//    @Test
//    public void testViewFeedbackById() {
//        String feedbackId = "1";
//        Feedback expectedFeedback = new Feedback(feedbackId, "user1", "issue1", "expert1", "Description");
//        when(issueMicroServiceClient.getfeedbackbyid(feedbackId)).thenReturn(expectedFeedback);
//
//        Feedback actualFeedback = userService.viewfeedbackbyid(feedbackId);
//
//        assertEquals(expectedFeedback, actualFeedback);
//    }
//
//    /**
//     * Test get all specs.
//     */
//    @Test
//    public void testGetAllSpecs() {
//        List<String> expectedSpecs = Arrays.asList("Java", "Python", "JavaScript");
//        when(expertMicroServicesClient.getallspecializations()).thenReturn(expectedSpecs);
//
//        List<String> actualSpecs = userService.getallspecs();
//
//        assertEquals(expectedSpecs, actualSpecs);
//    }
//    
//    /**
//     * Jwt parser test.
//     */
//    @Test
//    public void jwtParserTest() {
//    	Map<String, Object> expected = new HashMap<>();
////    	{role=user, credId=user1Yi7fu, username=user1}
//    	String dummytoken = jwtService.generateToken("user1","user", "user1Yi7fu");
//
//    	Map<String, Object> mp = jwtService.parseToken(dummytoken);
//    	System.out.println(mp);
//    	expected.put("role", "user");
//    	expected.put("credId", "user1Yi7fu");
//    	expected.put("username", "user1");
//    	assertEquals(expected, mp);
//    }
//    
//    /**
//     * Test method argument not valid exception.
//     */
//    @Test
//    public void testMethodArgumentNotValidException() {
//        GlobalExceptionHandler handler = new GlobalExceptionHandler();
//        BindingResult bindingResult = new BindException(new Object(), "object");
//        bindingResult.addError(new FieldError("object", "field", "Validation failed"));
//        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
//        ResponseEntity<ExceptionResponse> responseEntity = handler.getresponse(exception);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Validation failed", responseEntity.getBody().getMessage());
//    }
//
//
//    /**
//     * Test all exceptions.
//     */
//    @Test
//    public void testAllExceptions() {
//        GlobalExceptionHandler handler = new GlobalExceptionHandler();
//        Exception exception = new Exception("Internal Server Error");
//        ResponseEntity<ExceptionResponse> responseEntity = handler.allExceptions(exception);
//
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertEquals("Internal Server Error", responseEntity.getBody().getMessage());
//        
//    }    
//}
