package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    private Teacher teacher;
    //TODO
    //students

    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }
}
