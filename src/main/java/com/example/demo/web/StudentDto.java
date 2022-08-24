package com.example.demo.web;

import com.example.demo.domain.Course;
import com.example.demo.domain.Degree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String firstName;
    private String lastName;
    private String programme;
    private Degree degree;
    private String email;
    private List<Course> courses;

}
