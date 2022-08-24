package com.example.demo.service;

import com.example.demo.model.Teacher;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        this.teacherRepository = repository;
    }

    public Teacher createTeacher(Teacher teacher) {
        try{
            return teacherRepository.save(teacher);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException();
        }
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public Teacher getById(Long id){
        return teacherRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public boolean deleteTeacher(Long id){
        teacherRepository.deleteById(id);
        return !teacherRepository.existsById(id);
    }
}
