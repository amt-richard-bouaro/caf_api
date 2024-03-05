package com.amalitech.caf.services;

import com.amalitech.caf.dtos.auth.AuthResponseDto;
import com.amalitech.caf.dtos.auth.LoginPayload;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.exceptions.ConflictException;
import com.amalitech.caf.exceptions.UnauthorizedException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {
    AuthResponseDto register(UserEntity payload) throws NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException, ConflictException;

    AuthResponseDto login(LoginPayload payload) throws NoSuchAlgorithmException, InvalidKeySpecException;

    void logout(String token);

}
