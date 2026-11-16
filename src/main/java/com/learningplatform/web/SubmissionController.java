package com.learningplatform.web;

import com.learningplatform.entity.Submission;
import com.learningplatform.service.SubmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> submitAssignment(@RequestBody SubmissionRequest request) {
        Submission submission = submissionService.submitAssignment(
                request.assignmentId(),
                request.studentId(),
                request.content()
        );
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<Submission> gradeSubmission(@PathVariable Long id, @RequestBody GradeRequest request) {
        Submission submission = submissionService.gradeSubmission(id, request.score(), request.feedback());
        return ResponseEntity.ok(submission);
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignment(assignmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStudent(studentId));
    }

    public record SubmissionRequest(Long assignmentId, Long studentId, String content) {}
    public record GradeRequest(Integer score, String feedback) {}
}