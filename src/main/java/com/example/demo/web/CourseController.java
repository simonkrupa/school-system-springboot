package com.example.demo.web;

import com.example.demo.model.Course;
import com.example.demo.model.dto.CourseDto;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Course>> getAll(){
        return new ResponseEntity<>(courseService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(courseService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        if(courseService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> create(@RequestBody CourseDto body){
        return new ResponseEntity<>(courseService.create(new Course(body.getName(), body.getTeacher(), body.getStudents())), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{courseid}/add/{teacherid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> addTeacherToCourse(@PathVariable("courseid") Long courseId, @PathVariable("teacherid") Long teacherId){
        return new ResponseEntity<>(courseService.addTeacherToCourse(courseId, teacherId), HttpStatus.OK);
    }
}
