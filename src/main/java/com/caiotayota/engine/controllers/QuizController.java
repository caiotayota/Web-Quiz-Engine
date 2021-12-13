package com.caiotayota.engine.controllers;

import com.caiotayota.engine.entities.QuizCompleted;
import com.caiotayota.engine.entities.Quiz;
import com.caiotayota.engine.entities.QuizResult;
import com.caiotayota.engine.entities.UserAnswer;
import com.caiotayota.engine.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class QuizController {
    
    private final QuizService quizService;
    
    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    
    @PostMapping(path = "/quizzes", produces = APPLICATION_JSON_VALUE)
    public Quiz createQuiz(@RequestBody @Valid Quiz quizToSave,
                           @Autowired Principal principal) {
        return quizService.createQuiz(quizToSave, principal.getName());
    }
    
    @GetMapping(path = "/quizzes/{id}", produces = APPLICATION_JSON_VALUE)
    public Quiz getQuiz(@PathVariable long id) {
        return quizService.getQuiz(id);
    }
    
    @GetMapping(path = "/quizzes", produces = APPLICATION_JSON_VALUE)
    public Page<Quiz> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getAllQuizzes(page);
    }
    
    @GetMapping(path = "/quizzes/completed", produces = APPLICATION_JSON_VALUE)
    public Page<QuizCompleted> getAllCompletedQuizzes(@RequestParam(defaultValue = "0") int page,
                                                      @Autowired Principal principal) {
        return quizService.getAllCompletedQuizzes(page, principal.getName());
    }
    
    @PostMapping(path = "/quizzes/{id}/solve", produces = APPLICATION_JSON_VALUE)
    public QuizResult solveQuiz(@PathVariable long id,
                                @RequestBody UserAnswer userAnswer,
                                @Autowired Principal principal) {
        return quizService.checkQuizSubmission(id, userAnswer, principal.getName());
        
    }
    
    @DeleteMapping("quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id,
                           @Autowired Principal principal) {
        quizService.deleteQuiz(id, principal.getName());
    }
    
}
