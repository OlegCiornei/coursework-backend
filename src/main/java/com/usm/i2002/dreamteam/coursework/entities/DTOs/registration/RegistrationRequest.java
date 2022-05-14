package com.usm.i2002.dreamteam.coursework.entities.DTOs.registration;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.carts.CartRequest;
import com.usm.i2002.dreamteam.coursework.entities.Role;
import com.usm.i2002.dreamteam.coursework.entities.Status;
import com.usm.i2002.dreamteam.coursework.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {
    private final String email;
    private final String password;
    private final List<CartRequest> cart;

    public static User to(final RegistrationRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .status(Status.ACTIVE)
                .build();
    }
}
