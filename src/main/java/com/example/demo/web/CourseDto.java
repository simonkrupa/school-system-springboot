package com.example.demo.web;

import com.example.demo.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String name;
    private Teacher teacher;
}
