package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student){
        return repository.save(student);
    }

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public Student getById(Long id){
        return repository.getById(id);
    }

    public boolean deleteStudent(Student student){
        repository.delete(student);
        return !repository.existsById(student.getId());
    }
}
