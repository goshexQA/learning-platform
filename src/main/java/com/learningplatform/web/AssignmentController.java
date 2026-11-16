package com.learningplatform.web;

import com.learningplatform.entity.Assignment;
import com.learningplatform.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody AssignmentRequest request) {
        Assignment assignment = assignmentService.createAssignment(
                request.title(),
                request.description(),
                LocalDate.parse(request.dueDate()),
                request.maxScore(),
                request.lessonId()
        );
        return new ResponseEntity<>(assignment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByLesson(lessonId));
    }

    public record AssignmentRequest(String title, String description, String dueDate, Integer maxScore, Long lessonId) {}
}