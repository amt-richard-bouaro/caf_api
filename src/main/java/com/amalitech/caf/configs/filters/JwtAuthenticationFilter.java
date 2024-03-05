package com.amalitech.caf.configs.filters;

import com.amalitech.caf.entities.TokenEntity;
import com.amalitech.caf.exceptions.UnauthorizedException;
import com.amalitech.caf.repositories.UserRepository;
import com.amalitech.caf.services.JwtService;
import com.amalitech.caf.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    private final TokenService tokenService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {


        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authHeader.substring(7);

        final String userEmail;


        try {
            userEmail = jwtService.extractUserEmail(jwt);
        } catch (Exception e) {
            throw new UnavailableException("Unauthorized " + e.getMessage());
        }

        TokenEntity userBeingAuthenticatedToken = tokenService.getToken(userEmail);

        if (userBeingAuthenticatedToken == null || !userBeingAuthenticatedToken.getEmail().equals(userEmail)) {
            throw new UnavailableException("Invalid token");
        }

        if (userBeingAuthenticatedToken.getExpiryDate().isBefore(LocalDateTime.from(Instant.now()))) {
            tokenService.deleteToken(userEmail, jwt);
        }

        if (userEmail != null && SecurityContextHolder.getContext()
                .getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);


            try {
                if (jwtService.isTokenValid(jwt, userDetails) && userDetails != null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);
                }
            } catch (Exception e) {
                throw new UnavailableException("Unauthorized " + e.getMessage());
            }

        }

        filterChain.doFilter(request, response);


    }
}
