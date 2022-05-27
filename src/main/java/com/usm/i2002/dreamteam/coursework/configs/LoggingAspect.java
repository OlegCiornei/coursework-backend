package com.usm.i2002.dreamteam.coursework.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";


    @Around("execution(* com.usm.i2002.dreamteam.coursework.controllers..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final ObjectMapper objectMapper = new ObjectMapper();//TODO adopt for errors
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        final String methodName = methodSignature.getName();
        final String requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
        final String arguments = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());

        LOGGER.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " ]  REQUEST HANDLED FOR " +
                ANSI_RED + "/" + methodName + ANSI_RESET + " on " + requestURI + " with request parameters : " + arguments);

        final ResponseEntity result = (ResponseEntity) proceedingJoinPoint.proceed();

        LOGGER.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " ]  " +
                result.getStatusCode() + " " + objectMapper.writeValueAsString(result.getBody()) +
                " for " + ANSI_RED + "/" + methodName + ANSI_RESET + " on " + requestURI);

        return result;
    }
}