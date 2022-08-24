package com.example.demo.web;

import com.example.demo.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<Course> courses;
}
