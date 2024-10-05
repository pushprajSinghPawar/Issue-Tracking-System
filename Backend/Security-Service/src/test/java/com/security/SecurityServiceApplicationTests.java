package com.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.security.exception.ExceptionResponse;
import com.security.exception.GlobalExceptionHandler;
import com.security.exception.UserNotAddedException;
import com.security.model.Expert;
import com.security.model.User;
import com.security.model.credentials;
import com.security.repository.AuthRepo;
import com.security.service.AuthServiceImpl;
import com.security.service.JwtService;


// TODO: Auto-generated Javadoc
/**
 * The Class SecurityServiceApplicationTests.
 */
@SpringBootTest
class SecurityServiceApplicationTests {
	

    /** The password encoder. */
    @Mock
    private PasswordEncoder passwordEncoder;

    /** The jwt service. */
    @Mock
    private JwtService jwtService;

    /** The auth repo. */
    @Mock
    private AuthRepo authRepo;

    /** The auth service. */
    @InjectMocks
    private AuthServiceImpl authService;

    /**
     * Sets the up.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    /**
     * Jwt parser test.
     */
    @Test
    public void jwtParserTest() {
    	Map<String, Object> expected = new HashMap<>();
    	JwtService jwtService= new JwtService();
    	String dummytoken = jwtService.generateToken("user1","user", "user1Yi7fu");

    	assertNotNull(dummytoken);
    	assertTrue(jwtService.validateToken(dummytoken));
    	assertFalse(jwtService.validateToken("Invalid Token"));
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
     * Test user not added exception.
     */
    @Test
    public void TestUserNotAddedException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        UserNotAddedException exception = new UserNotAddedException("User cannt be added");
        ResponseEntity<ExceptionResponse> responseEntity = handler.getresponse2(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User cannt be added", responseEntity.getBody().getMessage());
    }

    /**
     * Test add user 2.
     */
    @Test
    public void testAddUser2() {
        // Arrange
        credentials credential = new credentials(null, null, null, null);
        credential.setUsername("testUser");
        credential.setPassword("testPassword");
        
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(credential.getPassword())).thenReturn(encodedPassword);

        credentials savedCredential = new credentials(encodedPassword, encodedPassword, encodedPassword, encodedPassword);
        savedCredential.setCredentialsId("1");
        savedCredential.setUsername("testUser");
        savedCredential.setPassword(encodedPassword);
        when(authRepo.save(credential)).thenReturn(savedCredential);

        // Act
        credentials result = authService.addUser(credential);

        // Assert
        assertNotNull(result);
        assertEquals(savedCredential.getCredentialsId(), result.getCredentialsId());
        assertEquals(savedCredential.getUsername(), result.getUsername());
        assertEquals(savedCredential.getPassword(), result.getPassword());

    }
    
	
	/**
	 * Test credentials constructor and getters.
	 */
	@Test
    public void testCredentialsConstructorAndGetters() {
        String credentialsId = "123";
        String username = "testUser";
        String password = "testPassword";
        String role = "ROLE_USER";

        credentials credentials = new credentials(credentialsId, username, password, role);

        assertEquals(credentialsId, credentials.getCredentialsId());
        assertEquals(username, credentials.getUsername());
        assertEquals(role, credentials.getRole());
        assertEquals(password, credentials.getPassword());
    }

    /**
     * Test credentials setters.
     */
    @Test
    public void testCredentialsSetters() {
    	credentials credentials = new credentials(null, null, null, null);

        String credentialsId = "123";
        String username = "testUser";
        String password = "testPassword"; 
        String role = "ROLE_USER";

        credentials.setCredentialsId(credentialsId);
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setRole(role);

        assertEquals(credentialsId, credentials.getCredentialsId());
        assertEquals(username, credentials.getUsername());
        assertEquals(role, credentials.getRole());
        assertTrue(credentials.toString().length()>0);
    }
    
    /**
     * Test expert constructor and getters.
     */
    @Test
    public void testExpertConstructorAndGetters() {
        String expertid = "123";
        String name = "John Doe";
        String password = "password";
        List<String> specialistIn = Arrays.asList("Java", "Spring");
        List<String> issuesids = Arrays.asList("1", "2");
        List<String> feedbackids = Arrays.asList("1", "2");
 
        Expert expert = new Expert(expertid, name, password, specialistIn, issuesids, feedbackids);

        assertEquals(expertid, expert.getExpertid());
        assertEquals(name, expert.getName());
        assertEquals(password, expert.getPassword());
        assertEquals(specialistIn, expert.getSpecialistIn());
        assertEquals(issuesids, expert.getIssuesids());
        assertEquals(feedbackids, expert.getFeedbackids());
    }

    /**
     * Test expert setters.
     */
    @Test
    public void testExpertSetters() {
        Expert expert = new Expert(null, null, null, null, null, null);

        String expertid = "123";
        String name = "John Doe";
        String password = "password";
        List<String> specialistIn = Arrays.asList("Java", "Spring");
        List<String> issuesids = Arrays.asList("1", "2");
        List<String> feedbackids = Arrays.asList("1", "2");
        List<String> roles=Arrays.asList("expert");

        expert.setExpertid(expertid);
        expert.setName(name);
        expert.setPassword(password);
        expert.setSpecialistIn(specialistIn);
        expert.setIssuesids(issuesids);
        expert.setFeedbackids(feedbackids);
        expert.setRoles(roles);

        assertEquals(expertid, expert.getExpertid());
        assertEquals(name, expert.getName());
        assertEquals(password, expert.getPassword());
        assertEquals(specialistIn, expert.getSpecialistIn());
        assertEquals(issuesids, expert.getIssuesids());
        assertEquals(feedbackids, expert.getFeedbackids());
        assertTrue(expert.getRoles().size()>0);
        assertTrue(expert.toString().length() > 0);
    }
    

    /**
     * Test user constructor and getters.
     */
    @Test
    public void testUserConstructorAndGetters() {
        String userid = "user123";
        String name = "John Doe";
        String position = "Developer";
        String companyName = "Example Company";
        String password = "password";
        List<String> issuesids = Arrays.asList("1", "2");
        List<String> feedbacksids = Arrays.asList("1", "2");

        User user = new User(userid, name, position, companyName, password, issuesids, feedbacksids);

        assertEquals(userid, user.getUserid());
        assertEquals(name, user.getName());
        assertEquals(position, user.getPosition());
        assertEquals(companyName, user.getCompanyName());
        assertEquals(password, user.getPassword());
        assertEquals(issuesids, user.getIssues());
        assertEquals(feedbacksids, user.getFeedBacks());
    }

    /**
     * Test user setters.
     */
    @Test
    public void testUserSetters() {
        User user = new User(null, null, null, null, null, null, null);
        
        String userid = "user123";
        String name = "John Doe";
        String position = "Developer";
        String companyName = "Example Company";
        String password = "password";
        List<String> issuesids = Arrays.asList("1", "2");
        List<String> feedbacksids = Arrays.asList("1", "2");
 
        user.setUserid(userid);
        user.setName(name);
        user.setPosition(position);
        user.setCompanyName(companyName);
        user.setPassword(password);
        user.setIssues(issuesids);
        user.setFeedBacks(feedbacksids);
        user.setRoles(Arrays.asList("user"));

        assertEquals(userid, user.getUserid());
        assertEquals(name, user.getName());
        assertEquals(position, user.getPosition());
        assertEquals(companyName, user.getCompanyName());
        assertEquals(password, user.getPassword());
        assertEquals(issuesids, user.getIssues());
        assertEquals(feedbacksids, user.getFeedBacks());
        assertEquals(Arrays.asList("user"), user.getRoles());
        assertTrue(user.toString().length()>0);
    }

    

}
