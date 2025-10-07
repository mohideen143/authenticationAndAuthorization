package com.Auth.authenticationAndAuthorization.Security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.Auth.authenticationAndAuthorization.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {
	
	private String SECRET_KEY = "qwaesrdfghjbkjhgfdsdjnbvsgdhgyaa";

// 	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);	 
 	private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
 			 
    private final long EXPIRATION_TIME = 1000 * 60 * 60; 

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) 
                .claim("userId", user.getId()) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token , UserDetails userDetails) {
        try {
            Jwts.parserBuilder()
            	.setSigningKey(key)
            	.build()
            	.parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    public Long extractUserId(HttpServletRequest request) {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        		String token = authHeader.substring(7);
//        		extractUserId(token);
        		Claims claims = extractAllClaims(token);
    	        return claims.get("userId", Long.class);
        }
        
        return null;
    }
    
//    public Long extractUserId(String token) {
//    	
//        Claims claims = extractAllClaims(token);
//        return claims.get("userId", Long.class);
//    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
