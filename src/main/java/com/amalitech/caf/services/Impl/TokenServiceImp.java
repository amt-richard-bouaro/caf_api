package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.auth.Token;
import com.amalitech.caf.entities.TokenEntity;
import com.amalitech.caf.repositories.TokenRepository;
import com.amalitech.caf.services.TokenService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TokenServiceImp implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImp(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public TokenEntity saveToken(Token payload) {
        TokenEntity newToken = TokenEntity.builder()
                .expiryDate(LocalDateTime.from(Instant.now()))
                .token(payload.getToken())
                .email(payload.getEmail())
                .build();

        return tokenRepository.save(newToken);
    }

    @Override
    public TokenEntity getToken(String email) {
        return tokenRepository.findByEmail(email);
    }

    @Override
    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    @Override
    public void deleteToken(String email, String token) {
        tokenRepository.deleteByEmailAndToken(email, token);
    }

}
