package com.oauthprovider.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @Autowired
    ErrorResponse errorResponse;

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
