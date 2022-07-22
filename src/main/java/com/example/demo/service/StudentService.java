package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.domain.StudentRepository;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) throws BadRequestException{
        try{
            return repository.save(student);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException();
        }
    }

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public Student getById(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public boolean deleteStudent(Long id){
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
