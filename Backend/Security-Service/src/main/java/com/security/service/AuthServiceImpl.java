package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.model.credentials;
import com.security.repository.AuthRepo;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthServiceImpl.
 */
@Service
public class AuthServiceImpl implements AuthService{
	
	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/** The jwt service. */
	@Autowired
	private JwtService jwtService;
	
	/** The auth repo. */
	@Autowired
	private AuthRepo authRepo;

	/**
	 * Adds the user.
	 *
	 * @param credential the credential
	 * @return the credentials
	 */
	@Override
	public credentials addUser(credentials credential) {
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		return authRepo.save(credential);
	}

	/**
	 * Generate token.
	 *
	 * @param username the username
	 * @param role the role
	 * @param credId the cred id
	 * @return the string
	 */
	@Override
	public String generateToken(String username, String role, String credId) {
		return jwtService.generateToken(username, role, credId);
	}

	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	@Override
	public boolean validateToken(String token) {
		return jwtService.validateToken(token);
	}

	@Override
	public boolean uniqueOrNot(String name) {
		return authRepo.findByUsername(name).isEmpty();
	}

}
