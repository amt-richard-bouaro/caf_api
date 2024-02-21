package com.amalitech.caf.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String generateToken(UserDetails user) throws NoSuchAlgorithmException, InvalidKeySpecException;
    
    String extractUserEmail(String token) throws Exception;
    
    boolean isTokenValid(
            String token,
            UserDetails user
    ) throws Exception;
    
    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails user
    ) throws NoSuchAlgorithmException, InvalidKeySpecException;
    
    <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) throws Exception;
    
}
