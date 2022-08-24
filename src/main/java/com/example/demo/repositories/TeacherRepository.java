package com.example.demo.repositories;


import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsTeacherByEmail(String email);
}
