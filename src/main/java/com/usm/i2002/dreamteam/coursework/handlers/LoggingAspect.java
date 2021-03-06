package com.usm.i2002.dreamteam.coursework.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Aspect
@Component
@SuppressWarnings("rawtypes")
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* com.usm.i2002.dreamteam.coursework.controllers..*(..))))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final String requestURI = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI();

        String arguments = null;
        if (stream(((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes()).noneMatch(aClass -> aClass == HttpServletRequest.class || aClass == HttpServletResponse.class))
            arguments = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());

        if (!methodSignature.getName().equals("exception")) {
            final String authorization = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            final String header = isNull(authorization) ? "" : " WITH AUTHORIZATION : " + authorization;
            final String requestParams = nonNull(arguments) && arguments.equals("[]") ? "" : " WITH REQUEST PARAMETERS : " + arguments;
            LOGGER.info(": REQUEST HANDLED ON " + requestURI + header + requestParams);
        }

        final ResponseEntity result = (ResponseEntity) proceedingJoinPoint.proceed();

        LoggingUtils.logResponse(result.getStatusCode(), result.getBody(), requestURI);

        return result;
    }
}