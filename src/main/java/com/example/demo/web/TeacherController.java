package com.example.demo.web;

import com.example.demo.domain.Teacher;
import com.example.demo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getAll(){
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher getById(@PathVariable("id") Long id){
        return teacherService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id, HttpServletResponse response){
        Teacher teacher = teacherService.getById(id);
        if(teacher == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        boolean success = teacherService.deleteTeacher(teacher);
        if(!success){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Teacher create(@RequestBody TeacherDto body){
        Teacher teacher = new Teacher(body.getFirstName(), body.getLastName(), body.getEmail(), body.getCourses());
        return teacherService.createTeacher(teacher);
    }
}
