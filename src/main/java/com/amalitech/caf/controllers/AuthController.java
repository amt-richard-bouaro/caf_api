package com.amalitech.caf.controllers;

import com.amalitech.caf.dtos.entities.UserDto;
import com.amalitech.caf.dtos.response.AuthResponseDto;
import com.amalitech.caf.dtos.requests.LoginPayload;
import com.amalitech.caf.dtos.response.UsersResponseDto;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.exceptions.UnauthorizedException;
import com.amalitech.caf.mappers.UserMapper;
import com.amalitech.caf.services.AuthService;
import com.amalitech.caf.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    
    private final AuthService authService;
    
    private final UserMapper userMapper;
    
    private final UserService userService;
    
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UsersResponseDto>> users() {
        return new ResponseEntity<>(userService.findAllusers(), HttpStatus.OK);
    }
    
    @Operation(description = "Register new user", summary = "This endpoint allows new user registration", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized", responseCode = "403")})
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody UserDto newUser
    ) throws NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException {
        UserEntity userEntity = userMapper.mapFromDtoToEntity(newUser);
        
        AuthResponseDto res = authService.register(userEntity);
        
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginPayload payload) throws NoSuchAlgorithmException, InvalidKeySpecException, UsernameNotFoundException {
        
        AuthResponseDto auth = authService.login(payload);
        
        return new ResponseEntity<>(auth, HttpStatus.OK);
    }
}
