package com.learningplatform.web;

import com.learningplatform.entity.Quiz;
import com.learningplatform.entity.Question;
import com.learningplatform.entity.AnswerOption;
import com.learningplatform.entity.QuestionType;
import com.learningplatform.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequest request) {
        Quiz quiz = quizService.createQuiz(request.title(), request.timeLimit(), request.moduleId());
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }

    @PostMapping("/{quizId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Long quizId, @RequestBody QuestionRequest request) {
        Question question = quizService.addQuestionToQuiz(
                request.text(),
                QuestionType.valueOf(request.type()),
                quizId
        );
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PostMapping("/questions/{questionId}/options")
    public ResponseEntity<AnswerOption> addAnswerOption(@PathVariable Long questionId, @RequestBody AnswerOptionRequest request) {
        AnswerOption option = quizService.addAnswerOption(request.text(), request.isCorrect(), questionId);
        return new ResponseEntity<>(option, HttpStatus.CREATED);
    }

    public record QuizRequest(String title, Integer timeLimit, Long moduleId) {}
    public record QuestionRequest(String text, String type) {}
    public record AnswerOptionRequest(String text, boolean isCorrect) {}
}