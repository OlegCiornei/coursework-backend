package com.usm.i2002.dreamteam.coursework.entities;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorData {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
}
