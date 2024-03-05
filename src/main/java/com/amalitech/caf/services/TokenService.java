package com.amalitech.caf.services;

import com.amalitech.caf.dtos.auth.Token;
import com.amalitech.caf.entities.TokenEntity;

public interface TokenService {
    TokenEntity saveToken(Token payload);

    TokenEntity getToken(String email);

    void deleteToken(String token);
}
