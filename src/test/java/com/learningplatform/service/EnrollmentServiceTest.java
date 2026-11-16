package com.learningplatform.service;

import com.learningplatform.entity.Enrollment;
import com.learningplatform.entity.Course;
import com.learningplatform.entity.User;
import com.learningplatform.entity.Role;
import com.learningplatform.exception.BadRequestException;
import com.learningplatform.repository.UserRepository;
import com.learningplatform.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    private User student;
    private User teacher;
    private Course course;

    @BeforeEach
    void setUp() {
        student = userRepository.save(new User("Test Student", "test-student@example.com", Role.STUDENT));
        teacher = userRepository.save(new User("Test Teacher", "test-teacher@example.com", Role.TEACHER));
        course = courseRepository.save(new Course("Test Course", "Learn Test", teacher));
    }

    @Test
    void testEnrollStudent() {
        Enrollment enrollment = enrollmentService.enrollStudent(student.getId(), course.getId());
        
        assertNotNull(enrollment.getId());
        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
        assertEquals("Active", enrollment.getStatus());
    }

    @Test
    void testEnrollStudentAlreadyEnrolled() {
        enrollmentService.enrollStudent(student.getId(), course.getId());
        
        assertThrows(BadRequestException.class, () -> {
            enrollmentService.enrollStudent(student.getId(), course.getId());
        });
    }

    @Test
    void testUnenrollStudent() {
        enrollmentService.enrollStudent(student.getId(), course.getId());
        enrollmentService.unenrollStudent(student.getId(), course.getId());
        
        var enrollments = enrollmentService.getStudentEnrollments(student.getId());
        assertTrue(enrollments.isEmpty());
    }

    @Test
    void testGetStudentEnrollments() {
        enrollmentService.enrollStudent(student.getId(), course.getId());
        
        var enrollments = enrollmentService.getStudentEnrollments(student.getId());
        assertEquals(1, enrollments.size());
    }

    @Test
    void testGetCourseEnrollments() {
        enrollmentService.enrollStudent(student.getId(), course.getId());
        
        var enrollments = enrollmentService.getCourseEnrollments(course.getId());
        assertEquals(1, enrollments.size());
    }
}