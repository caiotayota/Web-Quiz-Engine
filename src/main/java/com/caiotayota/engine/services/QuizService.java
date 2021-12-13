package com.caiotayota.engine.services;

import com.caiotayota.engine.entities.Quiz;
import com.caiotayota.engine.entities.QuizCompleted;
import com.caiotayota.engine.entities.QuizResult;
import com.caiotayota.engine.entities.UserAnswer;
import com.caiotayota.engine.exceptions.QuizNotFoundException;
import com.caiotayota.engine.exceptions.UserNotAllowedException;
import com.caiotayota.engine.repositories.QuizCompletedRepository;
import com.caiotayota.engine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {
    
    private static final int PAGE_SIZE = 10;
    
    private QuizRepository quizRepo;
    private QuizCompletedRepository quizCompletedRepo;
    
    @Autowired
    public QuizService(QuizRepository quizRepo, QuizCompletedRepository quizCompletedRepo) {
        this.quizRepo = quizRepo;
        this.quizCompletedRepo = quizCompletedRepo;
    }
    
    public Quiz createQuiz(Quiz quiz, String loggedInUser) {
        quiz.setEmail(loggedInUser);
        return quizRepo.save(quiz);
    }
    
    public void deleteQuiz(long id, String loggedInUser) {
        if (getQuiz(id).getEmail().equals(loggedInUser)) {
            quizRepo.deleteById(id);
        } else {
            throw new UserNotAllowedException();
        }
    }
    
    public Quiz getQuiz(long id) {
        Optional<Quiz> quizFromDb = quizRepo.findById(id);
        if (quizFromDb.isEmpty()) throw new QuizNotFoundException();
        return quizFromDb.get();
    }
    
    public Page<Quiz> getAllQuizzes(int pageNo) {
        return quizRepo.findAll(PageRequest.of(pageNo, PAGE_SIZE));
    }
    
    public Page<QuizCompleted> getAllCompletedQuizzes(int pageNo, String loggedInUser) {
        return quizCompletedRepo.findAllCompletedQuizzes(loggedInUser, PageRequest.of(pageNo, PAGE_SIZE));
    }
    
    public QuizResult checkQuizSubmission(long quizId, UserAnswer userAnswer, String loggedInUser) {
        if (getQuiz(quizId).checkAnswer(userAnswer.getAnswer())) {
            quizCompletedRepo.save(new QuizCompleted(quizId, loggedInUser));
            return QuizResult.SUCCESS;
        } else {
            return QuizResult.FAIL;
        
        }
    }
    
}
