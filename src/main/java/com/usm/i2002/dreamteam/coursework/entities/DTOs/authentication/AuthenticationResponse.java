package com.usm.i2002.dreamteam.coursework.entities.DTOs.authentication;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
    private final String email;
    private final String token;
}
