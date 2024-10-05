package com.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.config.CustomUserDetails;
import com.security.model.credentials;
import com.security.repository.AuthRepo;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomUserDetailService.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	/** The auth repo. */
	@Autowired
	private AuthRepo authRepo;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<credentials> customer = authRepo.findByUsername(username);
		credentials temp = customer.orElseThrow( () -> new UsernameNotFoundException("Username not found!!"));
		return new CustomUserDetails(temp);
	}

}
