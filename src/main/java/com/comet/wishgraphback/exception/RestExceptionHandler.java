package com.comet.wishgraphback.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));

        final ApiError.ApiErrorBuilder apiErrorBuilder = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .path(req.getContextPath() + req.getServletPath());

        return new ResponseEntity<>(apiErrorBuilder.message(e.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

