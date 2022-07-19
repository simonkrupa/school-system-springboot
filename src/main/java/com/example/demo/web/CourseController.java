package com.example.demo.web;

import com.example.demo.domain.Course;
import com.example.demo.domain.Student;
import com.example.demo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAll(){
        return courseService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course getById(@PathVariable("id") Long id){
        return courseService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id, HttpServletResponse response){
        Course course = courseService.getById(id);
        if(course == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        boolean success = courseService.delete(course);
        if(!success){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Course create(@RequestBody CourseDto body){
        Course course = new Course(body.getName(), body.getTeacher());
        return courseService.create(course);
    }
}
