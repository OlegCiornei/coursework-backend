package com.usm.i2002.dreamteam.coursework.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorData {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
}
