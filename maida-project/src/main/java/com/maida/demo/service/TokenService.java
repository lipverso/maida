package com.maida.demo.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.maida.demo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private static final Long expirationTime = 1800000L;
	private final String key = "Maida Security";
	
	public String generateToken(User user) {
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("Teste JWT Api")
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	public Claims decodeToken(String authToken) {
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(authToken)
				.getBody();
	}

}
