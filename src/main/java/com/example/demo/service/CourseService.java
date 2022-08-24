package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.StudentRepository;
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
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public CourseService(CourseRepository repository, TeacherRepository teacherRepository, TeacherService teacherService, StudentRepository studentRepository, StudentService studentService) {
        this.courseRepository = repository;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
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
            if(course.getStudents() != null){
                List<Student> tmpStudents = new ArrayList<>();
                for (Student student: course.getStudents()) {
                    if(!studentRepository.existsById(student.getId())){
                        Student s = studentService.createStudent(new Student(
                                student.getFirstName(),
                                student.getLastName(),
                                student.getProgramme(),
                                student.getDegree(),
                                student.getEmail(),
                                null));
                        tmpStudents.add(s);
                    }else {
                        tmpStudents.add(studentService.getById(student.getId()));
                    }
                }
                course.setStudents(tmpStudents);
            }
            return courseRepository.save(course);
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException();
        }
    }
}

