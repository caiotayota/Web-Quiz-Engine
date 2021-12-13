package com.caiotayota.engine.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuizResult {

    SUCCESS(true, "Congratulations, you're right!"),
    FAIL(false, "Wrong answer! Please, try again.");
    
    private final boolean success;
    private final String feedback;
    
    
    QuizResult(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }
}
