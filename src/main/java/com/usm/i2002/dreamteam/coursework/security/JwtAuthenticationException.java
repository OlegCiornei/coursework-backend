package com.usm.i2002.dreamteam.coursework.security;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus httpStatus;

    public JwtAuthenticationException(final String msg) {
        super(msg);
    }

    public JwtAuthenticationException(final String msg, final HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}