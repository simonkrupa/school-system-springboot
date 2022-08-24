package com.example.demo.repositories;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM student WHERE id IN (SELECT student_id FROM course_students WHERE course_id IN (SELECT course.id FROM course WHERE teacher_id=:id))",
            nativeQuery = true)
    List<Student> findAllStudentsOfTeacher(@Param("id") Long id);
}
