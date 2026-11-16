package com.learningplatform.service;

import com.learningplatform.entity.Course;
import com.learningplatform.entity.User;
import com.learningplatform.entity.Role;
import com.learningplatform.entity.Category;
import com.learningplatform.exception.ResourceNotFoundException;
import com.learningplatform.repository.CourseRepository;
import com.learningplatform.repository.UserRepository;
import com.learningplatform.repository.CategoryRepository;
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
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private User teacher;
    private Category category;

    @BeforeEach
    void setUp() {
        teacher = userRepository.save(new User("Test Teacher", "test-teacher@example.com", Role.TEACHER));
        category = categoryRepository.save(new Category("Test Category"));
    }

    @Test
    void testCreateCourse() {
        Course course = courseService.createCourse("Test Java Course", "Learn Java", teacher.getId(), category.getId());
        
        assertNotNull(course.getId());
        assertEquals("Test Java Course", course.getTitle());
        assertEquals(teacher, course.getTeacher());
        assertEquals(category, course.getCategory());
    }

    @Test
    void testGetCourseById() {
        Course course = courseService.createCourse("Test Java Course", "Learn Java", teacher.getId(), category.getId());
        Course found = courseService.getCourseById(course.getId());
        
        assertEquals(course.getId(), found.getId());
        assertEquals("Test Java Course", found.getTitle());
    }

    @Test
    void testGetCourseByIdNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.getCourseById(99999L);
        });
    }

    @Test
    void testUpdateCourse() {
        Course course = courseService.createCourse("Test Java Course", "Learn Java", teacher.getId(), category.getId());
        Course updated = courseService.updateCourse(course.getId(), "Advanced Java", "Advanced topics");
        
        assertEquals("Advanced Java", updated.getTitle());
        assertEquals("Advanced topics", updated.getDescription());
    }

    @Test
    void testDeleteCourse() {
        Course course = courseService.createCourse("Test Java Course", "Learn Java", teacher.getId(), category.getId());
        courseService.deleteCourse(course.getId());
        
        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.getCourseById(course.getId());
        });
    }

    @Test
    void testAddModuleToCourse() {
        Course course = courseService.createCourse("Test Java Course", "Learn Java", teacher.getId(), category.getId());
        var module = courseService.addModuleToCourse(course.getId(), "Module 1", 1);
        
        assertNotNull(module.getId());
        assertEquals("Module 1", module.getTitle());
        assertEquals(1, module.getOrderIndex());
    }
}