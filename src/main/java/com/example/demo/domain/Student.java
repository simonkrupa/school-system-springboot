package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String programme;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    @Column(nullable = false, unique = true)
    private String email;
    @ManyToMany(mappedBy = "students")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private List<Course> courses;

    public Student(String firstName, String lastName, String programme, Degree degree, String email, List<Course> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.programme = programme;
        this.degree = degree;
        this.email = email;
        this.courses = courses;
    }

    public void addCourse(Course course){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

}
