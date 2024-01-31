package com.amalitech.caf.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServiceInterface {
    String generateToken(UserDetails userDetails);
}
