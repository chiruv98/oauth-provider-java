package com.oauthprovider.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/*
 * @author: Chiranjeevi
 */

@Data
public class ErrorResponse implements Serializable {
    
    private String requestId;
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
