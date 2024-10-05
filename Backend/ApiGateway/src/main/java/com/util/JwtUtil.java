package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtUtil.
 */
@Component
public class JwtUtil {


    /** The Constant SECRET. */
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    /**
     * Validate token.
     *
     * @param token the token
     */
    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }



    /**
     * Gets the sign key.
     *
     * @return the sign key
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    /**
     * Extract role.
     *
     * @param token the token
     * @return the string
     */
    public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("ROLE");
    }
}
