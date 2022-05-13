package com.usm.i2002.dreamteam.coursework.services;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.registration.RegistrationRequest;
import com.usm.i2002.dreamteam.coursework.entities.User;

public interface UserService {
    User getByEmail(final String email);

    User addUser(final RegistrationRequest request);
}
