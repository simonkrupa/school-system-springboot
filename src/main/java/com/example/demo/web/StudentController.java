package com.example.demo.web;

import com.example.demo.domain.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getAll(){
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getById(@PathVariable("id") Long id){
        return studentService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
        Student student = studentService.getById(id);
        if(student == null){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        boolean success = studentService.deleteStudent(student);
        if(!success){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student create(@RequestBody StudentDto body) {
        Student student = new Student(body.getFirstName(), body.getLastName(), body.getProgramme(), body.getDegree(), body.getEmail());
        return studentService.createStudent(student);
    }


}
