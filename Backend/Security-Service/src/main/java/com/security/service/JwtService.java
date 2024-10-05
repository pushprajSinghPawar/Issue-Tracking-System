package com.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtService.
 */
@Service
public class JwtService {
	
	/** The jwt secret. */
	private String jwtSecret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	
	/**
	 * Generate token.
	 *
	 * @param username the username
	 * @param role the role
	 * @param credId the cred id
	 * @return the string
	 */
	public String generateToken(String username,String role, String credId) {
		Map<String,String> claim = new HashMap<>();
		claim.put("ROLE", role);
		claim.put("credId", credId);

		return Jwts.builder()
				.setClaims(claim)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}
	
	/**
	 * Validate token.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
