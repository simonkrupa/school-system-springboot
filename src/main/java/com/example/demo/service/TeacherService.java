package com.example.demo.service;

import com.example.demo.domain.Teacher;
import com.example.demo.domain.TeacherRepository;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher createTeacher(Teacher teacher) {
        try {
            return repository.save(teacher);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException();
        }
    }

    public List<Teacher> getAllTeachers(){
        return repository.findAll();
    }

    public Teacher getById(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public boolean deleteTeacher(Long id){
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
