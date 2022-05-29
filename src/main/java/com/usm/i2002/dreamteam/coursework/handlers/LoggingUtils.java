package com.usm.i2002.dreamteam.coursework.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

public class LoggingUtils {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void logResponse(final HttpStatus statusCode, final T body, final String requestURI) throws JsonProcessingException {
        objectMapper.findAndRegisterModules();
        LOGGER.info(statusCode + " " + objectMapper.writeValueAsString(body) + " ON " + requestURI);
    }
}
