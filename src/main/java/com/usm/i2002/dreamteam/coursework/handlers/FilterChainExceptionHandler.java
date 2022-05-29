package com.usm.i2002.dreamteam.coursework.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.resolve;

@Component
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    private final HandlerExceptionResolver resolver;

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    public FilterChainExceptionHandler(final @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

            final ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);

            if (response.getStatus() == HttpServletResponse.SC_FORBIDDEN)
                LoggingUtils.logResponse(resolve(response.getStatus()), "WITH AUTHORIZATION : " + request.getHeader(AUTHORIZATION), request.getRequestURI());

        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}