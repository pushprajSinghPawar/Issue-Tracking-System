package com.security.controller;



import jakarta.validation.Valid;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.exception.UserNotAddedException;
import com.security.externalClients.ExpertClient;
import com.security.externalClients.UserClient;
import com.security.model.Expert;
import com.security.model.User;
import com.security.model.credentials;
import com.security.repository.AuthRepo;
import com.security.service.AuthService;


// TODO: Auto-generated Javadoc
/**
 * The Class AuthController.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/** The auth service. */
	@Autowired
	private AuthService authService;

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/** The auth repo. */
	@Autowired
	private AuthRepo authRepo;
	
	/** The user client. */
	@Autowired
	UserClient userClient;

	/** The expert client. */
	@Autowired
	ExpertClient expertClient;
	
	/** The b crypt password encoder. */
	@Autowired 
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * Adds the user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws UserNotAddedException the user not added exception
	 * @throws Exception the exception
	 */
	@PostMapping("/addUser")
	public User addUser( @RequestBody @Valid User user) throws UserNotAddedException, Exception{
		System.out.println(user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserid(user.getName()+user.getPassword().substring(user.getPassword().length()-5).replaceAll("/","l"));
		user = userClient.addUser(user);
		if(user==null) {
			logger.error("User register not done");
		}else {
			logger.info("User registered done "+user);
		}
		return user;
	}
	
	/**
	 * Adds the cred.
	 *
	 * @param credential the credential
	 * @return the credentials
	 */
	@PostMapping("/addCred")
	public credentials addCred(@RequestBody credentials credential) {
		credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
		credential.setCredentialsId(credential.getUsername()+credential.getPassword().substring(credential.getPassword().length()-5).replaceAll("/","l"));
		return authRepo.save(credential);
	}
	
	/**
	 * Adds the expert.
	 *
	 * @param expert the expert
	 * @return the expert
	 * @throws UserNotAddedException the user not added exception
	 * @throws Exception the exception
	 */
	@PostMapping("/addExpert")
	public Expert addExpert(@RequestBody @Valid Expert expert) throws UserNotAddedException, Exception{
		expert.setPassword(bCryptPasswordEncoder.encode(expert.getPassword()));
		if(expert.getRoles()==null) {
			expert.setRoles(Arrays.asList("expert"));
		}
		expert.setExpertid(expert.getName()+expert.getPassword().substring(expert.getPassword().length()-5).replaceAll("/","l"));
		return expertClient.addExpert(expert);
	}

	/**
	 * Generate token.
	 *
	 * @param credential the credential
	 * @return the string
	 */
	@PostMapping("/generateToken")
	public String generateToken(@RequestBody credentials credential) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword()));
		if(authentication.isAuthenticated()) {
			credentials credential2 = authRepo.findByUsername(credential.getUsername()).orElseThrow();
			String tkn = authService.generateToken(credential2.getUsername(), credential2.getRole(), credential2.getCredentialsId());
			return tkn;
		}
		else {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	@GetMapping("/validateToken/{token}")
	public boolean validateToken(@PathVariable("token") String token) {
		return authService.validateToken(token);
	}

}
