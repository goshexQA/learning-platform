package com.learningplatform.service;

import com.learningplatform.entity.Assignment;
import com.learningplatform.entity.Lesson;
import com.learningplatform.exception.ResourceNotFoundException;
import com.learningplatform.repository.AssignmentRepository;
import com.learningplatform.repository.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final LessonRepository lessonRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, LessonRepository lessonRepository) {
        this.assignmentRepository = assignmentRepository;
        this.lessonRepository = lessonRepository;
    }

    public Assignment createAssignment(String title, String description, LocalDate dueDate, Integer maxScore, Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
        
        Assignment assignment = new Assignment(title, description, dueDate, maxScore, lesson);
        return assignmentRepository.save(assignment);
    }

    @Transactional(readOnly = true)
    public List<Assignment> getAssignmentsByLesson(Long lessonId) {
        return assignmentRepository.findByLessonId(lessonId);
    }

    @Transactional(readOnly = true)
    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + id));
    }
}