package com.arjun.ekart.security.jwt;



import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret}")
	public String secret;
	
	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiration;
	
	private SecretKey getSigningKey() {
		byte [] keyBytes = Decoders.BASE64.decode(secret);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(UserDetails userDetails ) {
		
		
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+accessTokenExpiration))
				.signWith(getSigningKey())
				.compact();
	}
	
	public String extractUserName(String token) {
		
		return extractAllClaims(token).getSubject();
	}
	


	public boolean isValid(UserDetails userDetails, String token) {
		// TODO Auto-generated method stub
		
		return extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return 	extractAllClaims(token).getExpiration().getTime() <
				System.currentTimeMillis();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
