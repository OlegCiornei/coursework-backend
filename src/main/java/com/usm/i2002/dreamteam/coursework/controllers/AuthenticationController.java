package com.usm.i2002.dreamteam.coursework.controllers;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.authentication.AuthenticationRequest;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.authentication.AuthenticationResponse;
import com.usm.i2002.dreamteam.coursework.entities.DTOs.registration.RegistrationRequest;
import com.usm.i2002.dreamteam.coursework.entities.User;
import com.usm.i2002.dreamteam.coursework.security.JwtTokenProvider;
import com.usm.i2002.dreamteam.coursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(final @RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            final User user = userService.getByEmail(request.getEmail());
            final String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            return ResponseEntity.ok(AuthenticationResponse.builder().email(request.getEmail()).token(token).build());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password combination");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(final @RequestBody RegistrationRequest request) {
        userService.addUser(request);

        final AuthenticationRequest authenticationRequest = new AuthenticationRequest(request.getEmail(), request.getPassword());

        ResponseEntity<AuthenticationResponse> response = authenticate(authenticationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
    }

    @PostMapping("/logout")
    public void logout(final HttpServletRequest request, final HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}