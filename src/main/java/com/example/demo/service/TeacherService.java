package com.example.demo.service;

import com.example.demo.domain.Teacher;
import com.example.demo.domain.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher createTeacher(Teacher teacher){ return repository.save(teacher);}

    public List<Teacher> getAllTeachers(){
        return repository.findAll();
    }

    public Teacher getById(Long id){
        return repository.getById(id);
    }

    public boolean deleteTeacher(Teacher teacher){
        repository.delete(teacher);
        return !repository.existsById(teacher.getId());
    }
}
