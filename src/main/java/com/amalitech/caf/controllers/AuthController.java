package com.amalitech.caf.controllers;

import com.amalitech.caf.dtos.auth.NewUserPayload;
import com.amalitech.caf.dtos.auth.AuthResponseDto;
import com.amalitech.caf.dtos.auth.LoginPayload;
import com.amalitech.caf.dtos.global.SuccessResponse;
import com.amalitech.caf.dtos.tournament.TournamentResponse;
import com.amalitech.caf.dtos.user.UsersResponseDto;
import com.amalitech.caf.entities.UserEntity;
import com.amalitech.caf.enums.ResponseStatus;
import com.amalitech.caf.exceptions.ConflictException;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.NullType;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
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
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UsersResponseDto>> users() {
        return new ResponseEntity<>(userService.findAllusers(), HttpStatus.OK);
    }

    @Operation(description = "Register new user", summary = "This endpoint allows new user registration", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized", responseCode = "403")})
    @PostMapping("/register")
//    @PostAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponseDto> register(
            @RequestBody NewUserPayload newUser
    ) throws NoSuchAlgorithmException, InvalidKeySpecException, UnauthorizedException, ConflictException {
        UserEntity userEntity = userMapper.mapFromDtoToEntity(newUser);

        AuthResponseDto registeredUser = authService.register(userEntity);

        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginPayload payload) throws NoSuchAlgorithmException, InvalidKeySpecException, UsernameNotFoundException {

        AuthResponseDto authenticatedUser = authService.login(payload);

        return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<SuccessResponse<NullType>> logout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() != null) {
            String token = authentication.getCredentials().toString();
            authService.logout(token);
        }
        SuccessResponse<NullType> response = new SuccessResponse<>(ResponseStatus.SUCCESS, "Logout successful", Instant.now().toString(), null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
