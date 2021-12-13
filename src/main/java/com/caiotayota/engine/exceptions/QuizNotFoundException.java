package com.caiotayota.engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuizNotFoundException extends RuntimeException {
    
    public QuizNotFoundException() {
        super("Quiz not found!");
    }
}
