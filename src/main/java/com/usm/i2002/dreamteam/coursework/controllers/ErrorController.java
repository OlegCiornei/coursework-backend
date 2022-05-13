package com.usm.i2002.dreamteam.coursework.controllers;

import com.usm.i2002.dreamteam.coursework.entities.ErrorData;
import com.usm.i2002.dreamteam.coursework.exceptions.NoSuchProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ErrorData> exception(final NoSuchProductException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(fillErrorData(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorData> exception(final BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(fillErrorData(e.getLocalizedMessage(), HttpStatus.UNAUTHORIZED));
    }

    private ErrorData fillErrorData(final String message, final HttpStatus status) {
        return ErrorData.builder()
                .message(message)
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();
    }

}