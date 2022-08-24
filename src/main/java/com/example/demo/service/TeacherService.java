package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.dto.TeacherDto;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TeacherService(TeacherRepository repository, StudentRepository studentRepository) {
        this.teacherRepository = repository;
        this.studentRepository = studentRepository;
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

    public List<Student> getAllStudentsOfTeacher(Long teacherId) {
        return studentRepository.findAllStudentsOfTeacher(teacherId);
    }

    public Teacher update(Long teacherId, TeacherDto body) {
        if(teacherRepository.existsById(teacherId)){
            Teacher teacher = teacherRepository.findById(teacherId).get();
            try {
                if (body.getFirstName() != null) {
                    teacher.setFirstName(body.getFirstName());
                }
                if (body.getEmail() != null) {
                    teacher.setEmail(body.getEmail());
                }
                if (body.getLastName() != null) {
                    teacher.setLastName(body.getLastName());
                }
                return this.teacherRepository.save(teacher);
            }catch (Exception e){
                throw new BadRequestException();
            }
        }
        throw new NotFoundException();
    }
}
