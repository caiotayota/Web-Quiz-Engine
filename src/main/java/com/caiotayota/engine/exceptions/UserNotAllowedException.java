package com.caiotayota.engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UserNotAllowedException extends RuntimeException {
    
    public UserNotAllowedException() {
        super("FORBIDDEN: You do not have permission to delete this quiz.");
    }
}
