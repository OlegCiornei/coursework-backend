package com.usm.i2002.dreamteam.coursework.handlers;

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

    @Around("execution(* com.usm.i2002.dreamteam.coursework.controllers..*(..))))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        final String requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();
        final String arguments = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());

        if (!methodSignature.getName().equals("exception"))
            LOGGER.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " ]  REQUEST HANDLED ON " +
                    requestURI + " WITH REQUEST PARAMETERS : " + arguments);

        final ResponseEntity result = (ResponseEntity) proceedingJoinPoint.proceed();

        LOGGER.info("[ " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " ]  " +
                result.getStatusCode() + " " + objectMapper.writeValueAsString(result.getBody()) +
                " ON " + requestURI);

        return result;
    }
}