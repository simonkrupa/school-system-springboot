package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.domain.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getAll(){
        return repository.findAll();
    }

    public Course getById(Long id){
        return repository.getById(id);
    }

    public boolean delete(Course course){
        repository.delete(course);
        return !repository.existsById(course.getId());
    }

    public Course create(Course course){
        return repository.save(course);
    }
}

