package com.example.demo.web;

import com.example.demo.domain.Student;
import com.example.demo.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String name;
    private Teacher teacher;
    private List<Student> students;
}
