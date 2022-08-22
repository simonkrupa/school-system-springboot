package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.domain.Teacher;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    @Autowired
    public CourseService(CourseRepository repository, TeacherRepository teacherRepository, TeacherService teacherService) {
        this.courseRepository = repository;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course getById(Long id){
        return courseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public boolean delete(Long id){
        courseRepository.deleteById(id);
        return !courseRepository.existsById(id);
    }

    public Course create(Course course){
        try{
            if (course.getTeacher() != null) {
                if (!teacherRepository.existsById(course.getTeacher().getId())) {
                    Teacher t = teacherService.createTeacher(new Teacher(course.getTeacher().getFirstName(),
                            course.getTeacher().getLastName(),
                            course.getTeacher().getEmail(),
                            new ArrayList<>()));
                    course.setTeacher(t);
                } else {
                    Teacher t = teacherService.getById(course.getTeacher().getId());
                    t.addCourse(course);
                    course.setTeacher(t);
                }
            }
            return courseRepository.save(course);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException();
        }
    }
}

