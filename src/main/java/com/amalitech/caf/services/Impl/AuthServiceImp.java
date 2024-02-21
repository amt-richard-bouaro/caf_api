package com.amalitech.caf.services.Impl;

import com.amalitech.caf.dtos.response.AuthResponseDto;
import com.amalitech.caf.dtos.requests.LoginPayload;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.enums.Role;
import com.amalitech.caf.exceptions.UnauthorizedException;
import com.amalitech.caf.repositories.UserRepository;
import com.amalitech.caf.services.AuthService;
import com.amalitech.caf.services.JwtService;

import com.amalitech.caf.services.MailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
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
public class AuthServiceImp implements AuthService {
    
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    private final MailService mailService;
    
    private final PasswordEncoder passwordEncoder;
    
    
    public AuthResponseDto register(UserEntity payload) throws NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException {
        UserEntity user = UserEntity.builder()
                                    .firstName(payload.getFirstName())
                                    .lastName(payload.getLastName())
                                    .email(payload.getEmail())
                                    .password(passwordEncoder.encode(payload.getPassword()))
                                    .role(Role.ADMIN)
                                    .build();
        
        UserEntity savedUser = userRepository.save(user);
        
        try {
            mailService.sendHtmlEmail(savedUser.getEmail(), "Account Registration Successful",
                    "<h1>Happy Browsing<h1>");
        } catch (MessagingException | UnsupportedEncodingException e) {
            
            throw new UnauthorizedException("Unable to send email");
        }
        
        
        String jwtToken = jwtService.generateToken(savedUser);
        
        return AuthResponseDto.builder()
                              .firstName(savedUser.getFirstName())
                              .lastName(savedUser.getLastName())
                              .email(savedUser.getEmail())
                              .role(savedUser.getRole())
                              .token(jwtToken)
                              .build();
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
        
        String jwtToken = jwtService.generateToken(user);
        
        return AuthResponseDto.builder()
                              .firstName(user.getFirstName())
                              .lastName(user.getLastName())
                              .email(user.getEmail())
                              .role(user.getRole())
                              .token(jwtToken)
                              .build();
        
    }
    
}
