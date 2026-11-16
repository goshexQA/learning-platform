-- Users
INSERT INTO users (name, email, role) VALUES ('Admin User', 'admin@platform.com', 'ADMIN');
INSERT INTO users (name, email, role) VALUES ('John Teacher', 'teacher@platform.com', 'TEACHER');
INSERT INTO users (name, email, role) VALUES ('Jane Student', 'student@platform.com', 'STUDENT');
INSERT INTO users (name, email, role) VALUES ('Bob Student', 'bob@platform.com', 'STUDENT');

-- Profiles
INSERT INTO profiles (user_id, bio, avatar_url) VALUES (1, 'Platform administrator', 'admin.png');
INSERT INTO profiles (user_id, bio, avatar_url) VALUES (2, 'Experienced Java developer', 'teacher.png');
INSERT INTO profiles (user_id, bio, avatar_url) VALUES (3, 'Computer Science student', 'student.png');

-- Categories
INSERT INTO categories (name) VALUES ('Programming');
INSERT INTO categories (name) VALUES ('Database');
INSERT INTO categories (name) VALUES ('Web Development');

-- Courses
INSERT INTO courses (title, description, category_id, teacher_id, duration, start_date) 
VALUES ('Java Fundamentals', 'Learn Java from scratch', 1, 2, '3 months', '2026-01-15');
INSERT INTO courses (title, description, category_id, teacher_id, duration, start_date) 
VALUES ('Spring Boot Masterclass', 'Advanced Spring Boot', 1, 2, '2 months', '2026-02-01');
INSERT INTO courses (title, description, category_id, teacher_id, duration, start_date) 
VALUES ('PostgreSQL Deep Dive', 'Master PostgreSQL', 2, 2, '6 weeks', '2026-01-20');

-- Modules
INSERT INTO modules (title, description, order_index, course_id) VALUES ('Introduction to Java', 'Basic concepts', 1, 1);
INSERT INTO modules (title, description, order_index, course_id) VALUES ('OOP Principles', 'Object-oriented programming', 2, 1);
INSERT INTO modules (title, description, order_index, course_id) VALUES ('Collections Framework', 'Java collections', 3, 1);

-- Lessons
INSERT INTO lessons (title, content, video_url, module_id) VALUES ('What is Java?', 'Java is a programming language...', 'https://video.com/1', 1);
INSERT INTO lessons (title, content, video_url, module_id) VALUES ('Installing JDK', 'How to install Java...', 'https://video.com/2', 1);
INSERT INTO lessons (title, content, video_url, module_id) VALUES ('Classes and Objects', 'Understanding OOP...', 'https://video.com/3', 2);

-- Assignments
INSERT INTO assignments (title, description, due_date, max_score, lesson_id) 
VALUES ('Hello World', 'Write your first Java program', '2026-02-01', 100, 1);
INSERT INTO assignments (title, description, due_date, max_score, lesson_id) 
VALUES ('OOP Exercise', 'Create a class hierarchy', '2026-02-15', 100, 3);

-- Enrollments
INSERT INTO enrollments (user_id, course_id, enroll_date, status) VALUES (3, 1, '2026-01-10', 'Active');
INSERT INTO enrollments (user_id, course_id, enroll_date, status) VALUES (4, 1, '2026-01-12', 'Active');
INSERT INTO enrollments (user_id, course_id, enroll_date, status) VALUES (3, 2, '2026-01-25', 'Active');

-- Tags
INSERT INTO tags (name) VALUES ('Java');
INSERT INTO tags (name) VALUES ('Spring');
INSERT INTO tags (name) VALUES ('Beginner');
INSERT INTO tags (name) VALUES ('Advanced');

-- Course-Tag relationships
INSERT INTO course_tag (course_id, tag_id) VALUES (1, 1);
INSERT INTO course_tag (course_id, tag_id) VALUES (1, 3);
INSERT INTO course_tag (course_id, tag_id) VALUES (2, 1);
INSERT INTO course_tag (course_id, tag_id) VALUES (2, 2);
INSERT INTO course_tag (course_id, tag_id) VALUES (2, 4);