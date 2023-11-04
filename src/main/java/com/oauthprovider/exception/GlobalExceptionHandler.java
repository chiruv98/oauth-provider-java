package com.oauthprovider.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends RuntimeException {

    @Autowired
    ErrorResponse errorResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException (MethodArgumentNotValidException ex, WebRequest request) {

        log.info("\n\n ---------- invoked ---------- \n\n");
        errorResponse.setRequestId(request.getSessionId());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getDescription(false));

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ErrorResponse handleCustomException(int status, String requestId, String message, String errorDesc, String uri) {

        errorResponse.setRequestId(requestId);
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setError(errorDesc);
        errorResponse.setMessage(message);
        errorResponse.setStatus(status);
        errorResponse.setPath(uri);

        return errorResponse;
    }

}
