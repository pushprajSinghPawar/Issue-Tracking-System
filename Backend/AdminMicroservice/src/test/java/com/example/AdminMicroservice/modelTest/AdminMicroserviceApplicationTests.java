//package com.example.AdminMicroservice.modelTest;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springdoc.core.customizers.OpenApiCustomizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//
//import com.example.AdminMicroservice.config.SwaggerConfig;
//import com.example.AdminMicroservice.controller.AdminController;
//import com.example.AdminMicroservice.exception.AdminPreviligeException;
//import com.example.AdminMicroservice.externalservice.ExpertMicroServiceClient;
//import com.example.AdminMicroservice.externalservice.IssueMicroServiceClient;
//import com.example.AdminMicroservice.externalservice.UserMicroServiceClient;
//import com.example.AdminMicroservice.model.Expert;
//import com.example.AdminMicroservice.model.Feedback;
//import com.example.AdminMicroservice.model.Issue;
//import com.example.AdminMicroservice.model.User;
//import com.example.AdminMicroservice.model.credentials;
//import com.example.AdminMicroservice.repository.AdminRepository;
//import com.example.AdminMicroservice.service.AdminService;
//import com.example.AdminMicroservice.service.AdminServiceImpl;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import jakarta.inject.Inject;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class AdminMicroserviceApplicationTests.
// */
//@SpringBootTest
//class AdminMicroserviceApplicationTests {
//	
//	/** The admin service. */
//	@Mock
//    private AdminService adminService;
//
//    /** The admin controller. */
//    @InjectMocks
//    private AdminController adminController;
//
//    /** The expert micro service client. */
//    @MockBean
//    private ExpertMicroServiceClient expertMicroServiceClient;
//
//    /** The user micro service client. */
//    @MockBean
//    private UserMicroServiceClient userMicroServiceClient;
//
//    /** The issue micro service client. */
//    @MockBean
//    private IssueMicroServiceClient issueMicroServiceClient;
//
//    /** The admin repository. */
//    @MockBean
//    private AdminRepository adminRepository;
//    
//
//    /**
//     * Delete cred with normal cred id.
//     */
//    @Test
//    public void deleteCredWithNormalCredId() {
//        // Arrange
//        Optional<credentials> cred = Optional.of(new credentials("99", "1name", "password1", "user"));
//        when(adminRepository.findById("99")).thenReturn(cred);
//
//        // Act and Assert
//        assertDoesNotThrow(() -> adminService.deleteUserByUserId("99"));
//    }
//    
//    
//    
//    
//    /**
//     * Test issue analystics for expert empty.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void testIssueAnalysticsForExpertEmpty() throws Exception {
//
//    	when(issueMicroServiceClient.getallFeedbacks()).thenReturn(Arrays.asList());
//    	Map<String, Map<String, Integer>> analytics = adminService.issueAnalysticsByExpert();
//    	Map<String, Map<String, Integer>> expected = new HashMap<>();
//    	Map<String, Integer> mp= new HashMap<>();
//    	assertEquals(analytics, expected);
//    }
//    
//    /**
//     * Test controller all users.
//     *
//     * @throws Exception the exception
//     */
//    @Test
//    public void testControllerAllUsers() throws Exception {
//    	
//    	credentials user1 = new credentials("1", "1name","password1","user");
//    	credentials user2 = new credentials("2", "2name","password2", "expert");
//    	
//    	when(adminService.allusers()).thenReturn(Arrays.asList(user1, user2));
//    	
//    	AdminController adminController = new AdminController();
//
//        adminController.adminService = adminService;
// 
//    	assertEquals(Arrays.asList(user1, user2)  ,adminController.alusers().getBody());
//    	
//    }
//    
//    @Test
//    void testControllerIssueAnalytics() throws Exception {
//    	HashMap<String, Integer> mp = new HashMap<>();
//    	mp.put("RESOLVED", 1);
//    	mp.put("UNRESOLVED", 1);
//    	when(adminService.issueAnalystics()).thenReturn(mp);
//
//    	AdminController adminController = new AdminController();
//
//        adminController.adminService = adminService;
//    	System.out.println("==="+adminController.issueAnalytics());
//    	assertEquals(adminController.issueAnalytics().getBody(), mp);
//    }
//    
//    @Test
//    void testControllerIssueAnalyticsExpert() throws Exception {
//    	Map<String, Map<String, Integer>> mp = new HashMap<>();
//    	Map<String, Integer> inner = new HashMap<>();
//    	inner.put("resolved", 2);
//    	inner.put("unresolved", 2);
//    	mp.put("exert", inner);
//    	when(adminService.issueAnalysticsByExpert()).thenReturn(mp);
//
//    	AdminController adminController = new AdminController();
//
//        adminController.adminService = adminService;
//    	assertEquals(adminController.issueAnalyticsForExpert().getBody(), mp);
//    }
//    
//    @Test
//    public void deleteCredWithAdmin() throws AdminPreviligeException, Exception {
//        // Arrange
//
//    	AdminController adminController = new AdminController();
//
//        adminController.adminService = adminService;
//        when(adminController.deleteUser("adminId")).thenThrow(AdminPreviligeException.class);
//        
//        assertThrows(AdminPreviligeException.class,()-> adminController.deleteUser("adminId"));
//        assertEquals(adminController.deleteUser("adminId").getStatusCode(), 404);
//
//    }
//    
//    @Test
//    void testControllerIssueAnalyticsUser() throws Exception {
//    	Map<String, Map<String, Integer>> mp = new HashMap<>();
//    	Map<String, Integer> inner = new HashMap<>();
//    	inner.put("total", 2);
//    	inner.put("resolved", 2);
//    	mp.put("user1", inner);
//    	when(adminService.issueAnalysticsByUser()).thenReturn(mp);
//
//    	AdminController adminController = new AdminController();
//
//        adminController.adminService = adminService;
//    	assertEquals(adminController.issueAnalyticsForUser().getBody(), mp);
//    }
//
//
//	/**
//	 * Context loads.
//	 */
//	@Test
//	void contextLoads() {
//	}
//	
//	/**
//	 * Test credentials constructor and getters.
//	 */
//	@Test
//    public void testCredentialsConstructorAndGetters() {
//        String credentialsId = "123";
//        String username = "testUser";
//        String password = "testPassword";
//        String role = "ROLE_USER";
//
//        credentials credentials = new credentials(credentialsId, username, password, role);
//
//        assertEquals(credentialsId, credentials.getCredentialsId());
//        assertEquals(username, credentials.getUsername());
//        assertEquals(role, credentials.getRole());
//    }
//
//    /**
//     * Test credentials setters.
//     */
//    @Test
//    public void testCredentialsSetters() {
//    	credentials credentials = new credentials();
//
//        String credentialsId = "123";
//        String username = "testUser";
//        String password = "testPassword"; 
//        String role = "ROLE_USER";
//
//        credentials.setCredentialsId(credentialsId);
//        credentials.setUsername(username);
//        credentials.setPassword(password);
//        credentials.setRole(role);
//
//        assertEquals(credentialsId, credentials.getCredentialsId());
//        assertEquals(username, credentials.getUsername());
//        assertEquals(role, credentials.getRole());
//        assertTrue(credentials.toString().length()>0);
//    }
//    
//    /**
//     * Test expert constructor and getters.
//     */
//    @Test
//    public void testExpertConstructorAndGetters() {
//        String expertid = "123";
//        String name = "John Doe";
//        String password = "password";
//        List<String> specialistIn = Arrays.asList("Java", "Spring");
//        List<String> issuesids = Arrays.asList("1", "2");
//        List<String> feedbackids = Arrays.asList("1", "2");
//        List<String> roles=Arrays.asList("expert");
// 
//        Expert expert = new Expert(expertid, name, password, specialistIn, issuesids, feedbackids,roles);
//
//        assertEquals(expertid, expert.getExpertid());
//        assertEquals(name, expert.getName());
//        assertEquals(password, expert.getPassword());
//        assertEquals(specialistIn, expert.getSpecialistIn());
//        assertEquals(issuesids, expert.getIssuesids());
//        assertEquals(feedbackids, expert.getFeedbackids());
//        assertTrue(expert.getRoles().size()>0);
//        assertTrue(expert.toString().length() > 0);
//    }
//
//    /**
//     * Test expert setters.
//     */
//    @Test
//    public void testExpertSetters() {
//        Expert expert = new Expert();
//
//        String expertid = "123";
//        String name = "John Doe";
//        String password = "password";
//        List<String> specialistIn = Arrays.asList("Java", "Spring");
//        List<String> issuesids = Arrays.asList("1", "2");
//        List<String> feedbackids = Arrays.asList("1", "2");
//        List<String> roles=Arrays.asList("expert");
//
//        expert.setExpertid(expertid);
//        expert.setName(name);
//        expert.setPassword(password);
//        expert.setSpecialistIn(specialistIn);
//        expert.setIssuesids(issuesids);
//        expert.setFeedbackids(feedbackids);
//        expert.setRoles(roles);
//
//        assertEquals(expertid, expert.getExpertid());
//        assertEquals(name, expert.getName());
//        assertEquals(password, expert.getPassword());
//        assertEquals(specialistIn, expert.getSpecialistIn());
//        assertEquals(issuesids, expert.getIssuesids());
//        assertEquals(feedbackids, expert.getFeedbackids());
//        assertTrue(expert.getRoles().size()>0);
//        assertTrue(expert.toString().length() > 0);
//    }
//	
//    /**
//     * Test feedback constructor and getters.
//     */
//    @Test
//    public void testFeedbackConstructorAndGetters() {
//        String feedbackid = "123";
//        String userid = "user123";
//        String issueid = "issue456";
//        String expertid = "expert789";
//        String feedbackDescription = "This is a feedback description";
//        Date feedbackDate = new Date();
//
//        Feedback feedback = new Feedback(feedbackid, userid, issueid, expertid, feedbackDescription);
//        feedback.setFeedbackgiven(feedbackDate);
//
//        assertEquals(feedbackid, feedback.getFeedbackid());
//        assertEquals(userid, feedback.getUserid());
//        assertEquals(issueid, feedback.getIssueid());
//        assertEquals(expertid, feedback.getExpertid());
//        assertEquals(feedbackDescription, feedback.getFeedbackDescription());
//        assertEquals(feedbackDate, feedback.getFeedbackgiven());
//    }
//
//    /**
//     * Test feedback setters.
//     */
//    @Test
//    public void testFeedbackSetters() {
//        Feedback feedback = new Feedback(null, null, null, null, null);
//        Date feedbackDate = new Date();
//
//        String feedbackid = "123";
//        String userid = "user123";
//        String issueid = "issue456";
//        String expertid = "expert789";
//        String feedbackDescription = "This is a feedback description";
//
//        feedback.setFeedbackid(feedbackid);
//        feedback.setUserid(userid);
//        feedback.setIssueid(issueid);
//        feedback.setExpertid(expertid);
//        feedback.setFeedbackDescription(feedbackDescription);
//        feedback.setFeedbackgiven(feedbackDate);
//
//        assertEquals(feedbackid, feedback.getFeedbackid());
//        assertEquals(userid, feedback.getUserid());
//        assertEquals(issueid, feedback.getIssueid());
//        assertEquals(expertid, feedback.getExpertid());
//        assertEquals(feedbackDescription, feedback.getFeedbackDescription());
//        assertEquals(feedbackDate, feedback.getFeedbackgiven());
//    }
//    
//    /**
//     * Test issue constructor and getters.
//     */
//    @Test
//    public void testIssueConstructorAndGetters() {
//        String issueid = "123";
//        String softwareName = "Example Software";
//        String softwareIssueType = "Bug";
//        String softwareIssueTitle = "Issue Title";
//        String softwareIssueDescription = "Issue Description";
//        boolean highPriority = true;
//        String status = "Open";
//        boolean paidTicket = false;
//        String userid = "user123";
//        Date dateissueform = new Date();
//
//        Issue issue = new Issue(issueid, softwareName, softwareIssueType, softwareIssueTitle, softwareIssueDescription, highPriority, status, paidTicket, userid);
//        issue.setDateissueform(dateissueform);
//
//        assertEquals(issueid, issue.getIssueid());
//        assertEquals(softwareName, issue.getSoftwareName());
//        assertEquals(softwareIssueType, issue.getSoftwareIssueType());
//        assertEquals(softwareIssueTitle, issue.getSoftwareIssueTitle());
//        assertEquals(softwareIssueDescription, issue.getSoftwareIssueDescription());
//        assertEquals(highPriority, issue.isHighPriority());
//        assertEquals(status, issue.getStatus());
//        assertEquals(paidTicket, issue.isPaidTicket());
//        assertEquals(userid, issue.getUserid());
//        assertEquals(dateissueform, issue.getDateissueform());
//        assertTrue(issue.toString().length()>0);
//    }
//
//    /**
//     * Test issue setters.
//     */
//    @Test
//    public void testIssueSetters() {
//        Issue issue = new Issue(null, null, null, null, null, false, null, false, null);
//        Date dateissueform = new Date();
//
//        String issueid = "123";
//        String softwareName = "Example Software";
//        String softwareIssueType = "Bug";
//        String softwareIssueTitle = "Issue Title";
//        String softwareIssueDescription = "Issue Description";
//        boolean highPriority = true;
//        String status = "Open";
//        boolean paidTicket = false;
//        String userid = "user123";
//
//        issue.setIssueid(issueid);
//        issue.setSoftwareName(softwareName);
//        issue.setSoftwareIssueType(softwareIssueType);
//        issue.setSoftwareIssueTitle(softwareIssueTitle);
//        issue.setSoftwareIssueDescription(softwareIssueDescription);
//        issue.setHighPriority(highPriority);
//        issue.setStatus(status);
//        issue.setPaidTicket(paidTicket);
//        issue.setUserid(userid);
//        issue.setDateissueform(dateissueform);
//
//        assertEquals(issueid, issue.getIssueid());
//        assertEquals(softwareName, issue.getSoftwareName());
//        assertEquals(softwareIssueType, issue.getSoftwareIssueType());
//        assertEquals(softwareIssueTitle, issue.getSoftwareIssueTitle());
//        assertEquals(softwareIssueDescription, issue.getSoftwareIssueDescription());
//        assertEquals(highPriority, issue.isHighPriority());
//        assertEquals(status, issue.getStatus());
//        assertEquals(paidTicket, issue.isPaidTicket());
//        assertEquals(userid, issue.getUserid());
//        assertEquals(dateissueform, issue.getDateissueform());
//    }
//    
//    /**
//     * Test user constructor and getters.
//     */
//    @Test
//    public void testUserConstructorAndGetters() {
//        String userid = "user123";
//        String name = "John Doe";
//        String position = "Developer";
//        String companyName = "Example Company";
//        String password = "password";
//        List<String> issuesids = Arrays.asList("1", "2");
//        List<String> feedbacksids = Arrays.asList("1", "2");
//
//        User user = new User(userid, name, position, companyName, password, issuesids, feedbacksids);
//
//        assertEquals(userid, user.getUserid());
//        assertEquals(name, user.getName());
//        assertEquals(position, user.getPosition());
//        assertEquals(companyName, user.getCompanyName());
//        assertEquals(password, user.getPassword());
//        assertEquals(issuesids, user.getIssues());
//        assertEquals(feedbacksids, user.getFeedBacks());
//        assertEquals(Arrays.asList("USER"), user.getRoles());
//    }
//
//    /**
//     * Test user setters.
//     */
//    @Test
//    public void testUserSetters() {
//        User user = new User(null, null, null, null, null, null, null);
//        
//        String userid = "user123";
//        String name = "John Doe";
//        String position = "Developer";
//        String companyName = "Example Company";
//        String password = "password";
//        List<String> issuesids = Arrays.asList("1", "2");
//        List<String> feedbacksids = Arrays.asList("1", "2");
// 
//        user.setUserid(userid);
//        user.setName(name);
//        user.setPosition(position);
//        user.setCompanyName(companyName);
//        user.setPassword(password);
//        user.setIssues(issuesids);
//        user.setFeedBacks(feedbacksids);
//
//        assertEquals(userid, user.getUserid());
//        assertEquals(name, user.getName());
//        assertEquals(position, user.getPosition());
//        assertEquals(companyName, user.getCompanyName());
//        assertEquals(password, user.getPassword());
//        assertEquals(issuesids, user.getIssues());
//        assertEquals(feedbacksids, user.getFeedBacks());
//        assertEquals(Arrays.asList("USER"), user.getRoles());
//    }
//     
//    /**
//     * Test custom open API.
//     */
//    @Test
//    public void testCustomOpenAPI() {
//        SwaggerConfig swaggerConfig = new SwaggerConfig();
//        OpenApiCustomizer customizer = swaggerConfig.customOpenAPI();
//        OpenAPI openApi = new OpenAPI();
//        customizer.customise(openApi);
//
//        assertEquals("Issue Tracking Software", openApi.getInfo().getTitle());
//        assertEquals("Our Spring boot web app allows you to post your issues to the experts that can help you resolve them", openApi.getInfo().getDescription());
//        assertEquals("Version 1", openApi.getInfo().getVersion());
//        assertEquals("pushpraj-singh.pawar@capgemini.com", openApi.getInfo().getContact().getEmail());
//        assertEquals("Pushp raj Singh Pawar", openApi.getInfo().getContact().getName());
//    }
//    
//   
//   
//}
