package com.oauthprovider.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

        log.info("\n\n---------------- INVOKED EXCEPTION HANDLER ----------------\n\n");

        List<String> errorMessages = new ArrayList<String>();
        for (FieldError msg : ex.getFieldErrors()) {
            errorMessages.add(msg.getDefaultMessage());
        }

        errorResponse.setRequestId(request.getSessionId());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(errorMessages);
        errorResponse.setPath(request.getDescription(false));

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException (HttpRequestMethodNotSupportedException ex, WebRequest request) {

        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add(ex.getMessage());

        errorResponse.setRequestId(request.getSessionId());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatus(ex.getStatusCode().value());
        errorResponse.setError((ex.getStatusCode().toString().split(" "))[1]);
        errorResponse.setMessage(errorMessages);
        errorResponse.setPath(request.getDescription(false));

        return new ResponseEntity<ErrorResponse>(errorResponse, ex.getStatusCode());
    }

    public ErrorResponse handleCustomException(int status, String requestId, List<String> message, String errorDesc, String uri) {

        errorResponse.setRequestId(requestId);
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setError(errorDesc);
        errorResponse.setMessage(message);
        errorResponse.setStatus(status);
        errorResponse.setPath(uri);

        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException (Exception ex, WebRequest request) {

        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add(ex.getMessage());

        errorResponse.setRequestId(request.getSessionId());
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatus(500);
        errorResponse.setError("INTERNAL_SERVER_ERROR");
        errorResponse.setMessage(errorMessages);
        errorResponse.setPath(request.getDescription(false));

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
