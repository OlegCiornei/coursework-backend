package com.usm.i2002.dreamteam.coursework.entities.DTOs.authentication;

import com.usm.i2002.dreamteam.coursework.entities.DTOs.carts.CartRequest;
import lombok.Data;

import java.util.List;

@Data
public class AuthenticationRequest {
    private final String email;
    private final String password;
    private final List<CartRequest> cart;
}