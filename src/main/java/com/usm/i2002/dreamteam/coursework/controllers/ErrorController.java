package com.usm.i2002.dreamteam.coursework.controllers;

import java.time.LocalDateTime;

import com.usm.i2002.dreamteam.coursework.entities.ErrorData;
import com.usm.i2002.dreamteam.coursework.exceptions.NoSuchProductException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ErrorData> exception(NoSuchProductException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(fillErrorData(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST));
    }

    private ErrorData fillErrorData(String message, HttpStatus status) {
        return ErrorData.builder()
                .message(message)
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();
    }

}