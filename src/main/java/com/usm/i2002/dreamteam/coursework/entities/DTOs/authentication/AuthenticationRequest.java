package com.usm.i2002.dreamteam.coursework.entities.DTOs.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private final String email;
    private final String password;
}