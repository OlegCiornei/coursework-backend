package com.usm.i2002.dreamteam.coursework.services.impl;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.registration.RegistrationRequest;
import com.usm.i2002.dreamteam.coursework.entities.User;
import com.usm.i2002.dreamteam.coursework.repositories.UserRepository;
import com.usm.i2002.dreamteam.coursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getByEmail(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User doesn't exists"));
    }

    @Override
    public User addUser(final RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new BadCredentialsException("Invalid email");

        validateNewUser(request);

        final User user = RegistrationRequest.to(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new BadCredentialsException("Failed to save user.");
        }

        return user;
    }

    private void validateNewUser(final RegistrationRequest request) {
        final String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if (!request.getEmail().matches(emailRegex))
            throw new BadCredentialsException("Invalid email");

        final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z]).{4,20}$";

        if (!request.getPassword().matches(passwordRegex))
            throw new BadCredentialsException("Invalid password");
    }
}
