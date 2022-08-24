package com.example.demo.web;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.model.dto.TeacherDto;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Teacher>> getAll(){
        return new ResponseEntity<>(teacherService.getAllTeachers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(teacherService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        if(this.teacherService.deleteTeacher(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> create(@RequestBody TeacherDto body){
        return new ResponseEntity<>(this.teacherService.createTeacher(new Teacher(body.getFirstName(), body.getLastName(), body.getEmail(), body.getCourses())), HttpStatus.CREATED);
    }

    @GetMapping(path = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudentsOfTeacher(@PathVariable("id") Long teacherId){
        return new ResponseEntity<>(this.teacherService.getAllStudentsOfTeacher(teacherId), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> update(@PathVariable("id") Long teacherId, @RequestBody TeacherDto body){
        return new ResponseEntity<>(this.teacherService.update(teacherId, body), HttpStatus.OK);
    }
}
