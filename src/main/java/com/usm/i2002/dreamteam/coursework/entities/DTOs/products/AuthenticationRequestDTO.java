package com.usm.i2002.dreamteam.coursework.entities.DTOs.products;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}