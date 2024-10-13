package com.security.service;

import org.springframework.stereotype.Service;

import com.security.model.credentials;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthService.
 */
@Service
public interface AuthService {
	
	/**
	 * Adds the user.
	 *
	 * @param customer the customer
	 * @return the credentials
	 */
	public credentials addUser(credentials customer);
	
	/**
	 * Generate token.
	 *
	 * @param username the username
	 * @param role the role
	 * @param credId the cred id
	 * @return the string
	 */
	public String generateToken(String username, String role, String credId);
	
	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	public boolean validateToken(String token);

	public boolean uniqueOrNot(String name);

}
