package com.learningplatform.web;

import com.learningplatform.entity.Enrollment;
import com.learningplatform.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody EnrollmentRequest request) {
        Enrollment enrollment = enrollmentService.enrollStudent(request.studentId(), request.courseId());
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> unenrollStudent(@RequestBody EnrollmentRequest request) {
        enrollmentService.unenrollStudent(request.studentId(), request.courseId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentEnrollments(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getCourseEnrollments(courseId));
    }

    public record EnrollmentRequest(Long studentId, Long courseId) {}
}