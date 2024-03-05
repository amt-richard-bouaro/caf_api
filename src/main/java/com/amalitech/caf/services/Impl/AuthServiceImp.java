package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.auth.AuthResponseDto;
import com.amalitech.caf.dtos.auth.LoginPayload;
import com.amalitech.caf.dtos.auth.Token;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.exceptions.ConflictException;
import com.amalitech.caf.exceptions.UnauthorizedException;
import com.amalitech.caf.repositories.UserRepository;
import com.amalitech.caf.services.AuthService;
import com.amalitech.caf.services.JwtService;

import com.amalitech.caf.services.MailService;
import com.amalitech.caf.services.TokenService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;


    public AuthResponseDto register(UserEntity payload) throws NoSuchAlgorithmException, InvalidKeySpecException, ConflictException, UnauthorizedException {

        UserEntity user = UserEntity.builder()
                .firstName(payload.getFirstName())
                .lastName(payload.getLastName())
                .email(payload.getEmail())
                .password(passwordEncoder.encode(payload.getPassword()))
                .role(payload.getRole())
                .build();

        UserEntity userAlreadyExists = userRepository.findByEmail(user.getEmail());

        if (userAlreadyExists != null) {
            throw new ConflictException("User already exists");
        }

        UserEntity savedUser = userRepository.save(user);

        try {
            mailService.sendHtmlEmail(savedUser.getEmail(), "Account Registration Successful",
                    "<h1>Happy Browsing<h1>");
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new UnauthorizedException("Unable to send email");
        }

        return generateAuthResponse(savedUser);
    }


    public AuthResponseDto login(LoginPayload payload) throws NoSuchAlgorithmException, InvalidKeySpecException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid email or password");
        }

        UserEntity user = userRepository.findByEmail(payload.getEmail());

        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password");
        }

        return generateAuthResponse(user);

    }


    public AuthResponseDto generateAuthResponse(UserEntity user) throws NoSuchAlgorithmException, InvalidKeySpecException {

        String jwtToken = jwtService.generateToken(user);

        Token token = Token.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .build();

        tokenService.saveToken(token);

        return AuthResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }


}
