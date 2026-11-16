package com.learningplatform.repository;

import com.learningplatform.entity.Course;
import com.learningplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
    List<Course> findByCategory_Name(String categoryName);
    
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.modules LEFT JOIN FETCH c.teacher WHERE c.id = :id")
    Course findCourseWithModulesAndTeacher(Long id);
}