package com.learningplatform.service;

import com.learningplatform.entity.Quiz;
import com.learningplatform.entity.Question;
import com.learningplatform.entity.AnswerOption;
import com.learningplatform.entity.Module;
import com.learningplatform.entity.QuestionType;
import com.learningplatform.exception.ResourceNotFoundException;
import com.learningplatform.repository.QuizRepository;
import com.learningplatform.repository.ModuleRepository;
import com.learningplatform.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final ModuleRepository moduleRepository;
    private final QuestionRepository questionRepository;

    public QuizService(QuizRepository quizRepository, ModuleRepository moduleRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.moduleRepository = moduleRepository;
        this.questionRepository = questionRepository;
    }

    public Quiz createQuiz(String title, Integer timeLimit, Long moduleId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Module not found with id: " + moduleId));
        
        Quiz quiz = new Quiz(title, timeLimit, module);
        return quizRepository.save(quiz);
    }

    public Question addQuestionToQuiz(String text, QuestionType type, Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        
        Question question = new Question(text, type, quiz);
        quiz.getQuestions().add(question);
        return question;
    }

    public AnswerOption addAnswerOption(String text, boolean isCorrect, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + questionId));
        
        AnswerOption option = new AnswerOption(text, isCorrect, question);
        question.getOptions().add(option);
        return option;
    }

    @Transactional(readOnly = true)
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }
}