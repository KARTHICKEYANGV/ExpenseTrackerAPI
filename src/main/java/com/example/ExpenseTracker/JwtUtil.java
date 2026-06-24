package com.example.ExpenseTracker;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;


@Component
public class JwtUtil {
    private final String SECRET = "your-256-bit-secret-key-which-should-be-very-long!"; // 32+ characters
    private final long EXPIRATION_TIME = 1000 * 30;
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email) {
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, io.jsonwebtoken.SignatureAlgorithm.HS256)
                .compact();
    }

}
