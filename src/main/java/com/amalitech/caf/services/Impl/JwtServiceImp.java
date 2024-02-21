package com.amalitech.caf.services.Impl;

import com.amalitech.caf.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImp implements JwtService {
    
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    
    @Value("${application.security.jwt.access-token.expiration}")
    private String accessTokenExpiresIn;
    
    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public boolean isTokenValid(
            String token,
            UserDetails user
    ) throws Exception {
        final String username = extractUserEmail(token);
        return ! isTokenExpired(token) && username.equals(user.getUsername());
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    @Override
    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }
    
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails user
    ) {
        return Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(user.getUsername())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() * Integer.parseInt(accessTokenExpiresIn)))
                   .signWith(getSignKey()) //SignatureAlgorithm.ES256
                   .compact();
    }
    
    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        
        return Jwts.parserBuilder()
                   .setSigningKey(getSignKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
    
    
    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
        
        return new SecretKeySpec(Base64.getDecoder()
                                       .decode(secretKey), SignatureAlgorithm.HS256.getJcaName());
        
        
    }
}
