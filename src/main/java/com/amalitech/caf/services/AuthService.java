package com.amalitech.caf.services;

import com.amalitech.caf.dtos.response.AuthResponseDto;
import com.amalitech.caf.dtos.requests.LoginPayload;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.exceptions.UnauthorizedException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {
    AuthResponseDto register(UserEntity payload) throws NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException;
    
    AuthResponseDto login(LoginPayload payload) throws NoSuchAlgorithmException, InvalidKeySpecException;
    
}
