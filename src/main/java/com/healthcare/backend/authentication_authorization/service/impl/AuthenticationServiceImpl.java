package com.healthcare.backend.authentication_authorization.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.healthcare.backend.domain.enums.Role;
import com.healthcare.backend.domain.entity.User;
import com.healthcare.backend.domain.request.AuthenticationRequest;
import com.healthcare.backend.domain.request.RegistrationRequest;
import com.healthcare.backend.domain.response.AuthenticationResponse;
import com.healthcare.backend.authentication_authorization.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final PasswordEncoder passwordEncoder;
    @NonNull
    private final JwtServiceImpl jwtServiceImpl;
    @NonNull
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user in the system.
     *
     * @param registrationRequest the registration request containing user details
     * @return an AuthenticationResponse containing the access and refresh tokens
     */
    public AuthenticationResponse register(RegistrationRequest registrationRequest) {
        var user = User.builder().firstName(registrationRequest.getFirstName()).lastName(registrationRequest.getLastName()).email(registrationRequest.getEmail()).password(passwordEncoder.encode(registrationRequest.getPassword())).role(Role.USER).build();

        log.info("AuthenticationResponse From Register: {}", user.toString());

        userRepository.save(user);
        var jwtToken = jwtServiceImpl.generateToken(Objects.requireNonNull(user));
        var refreshToken = jwtServiceImpl.generateRefreshToken(Objects.requireNonNull(user));

        log.info("Generated Token from Register Function: {}", jwtToken);

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    /**
     * Authenticates a user based on the provided authentication request.
     *
     * @param request the authentication request containing user credentials
     * @return an AuthenticationResponse containing the access and refresh tokens
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        log.info("AuthenticationResponse From Authenticate Function: {}", user);

        var jwtToken = jwtServiceImpl.generateToken(Objects.requireNonNull(user));
        var refreshToken = jwtServiceImpl.generateRefreshToken(user);

        log.info("Generated Token from Authenticate Function: {}", jwtToken);
        return AuthenticationResponse.builder().accessToken(Objects.requireNonNull(jwtToken)).refreshToken(Objects.requireNonNull(refreshToken)).build();
    }

    /**
     * Refreshes the authentication token.
     *
     * @param request  the HTTP request containing the refresh token
     * @param response the HTTP response to be sent back to the client
     * @throws IOException if an input or output exception occurs
     */
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authorizationHeader = request.getHeader(Objects.requireNonNull(HttpHeaders.AUTHORIZATION, "Authorization header cannot be null"));
        final String refreshToken;
        final String userEmail;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("Authorization Header is Null or Not Started with Bearer");
            return;
        }

        refreshToken = authorizationHeader.substring(7);
        userEmail = jwtServiceImpl.extractUserName(refreshToken);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = this.userRepository.findByEmail(userEmail).orElseThrow();
            log.info("User Details from Refresh Token: {}", userDetails);

            if (jwtServiceImpl.isTokenValidated(refreshToken, userDetails)) {

                var accessToken = jwtServiceImpl.generateToken(userDetails);
                log.info("Generated Token from Refresh Token Function: {}", accessToken);

                var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                log.info("Authentication Response from Refresh Token Function: {}", authResponse);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
