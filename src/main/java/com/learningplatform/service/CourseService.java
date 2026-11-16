package com.learningplatform.service;

import com.learningplatform.entity.Course;
import com.learningplatform.entity.User;
import com.learningplatform.entity.Category;
import com.learningplatform.entity.Module;
import com.learningplatform.exception.ResourceNotFoundException;
import com.learningplatform.repository.CourseRepository;
import com.learningplatform.repository.UserRepository;
import com.learningplatform.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public Course createCourse(String title, String description, Long teacherId, Long categoryId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));
        
        Course course = new Course(title, description, teacher);
        
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
            course.setCategory(category);
        }
        
        return courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public Course updateCourse(Long id, String title, String description) {
        Course course = getCourseById(id);
        if (title != null) course.setTitle(title);
        if (description != null) course.setDescription(description);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    public Module addModuleToCourse(Long courseId, String title, Integer orderIndex) {
        Course course = getCourseById(courseId);
        Module module = new Module(title, orderIndex, course);
        course.getModules().add(module);

        courseRepository.save(course);
        return module;
    }
}