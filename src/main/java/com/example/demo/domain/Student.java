package com.example.demo.domain;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
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

    public Student(String firstName, String lastName, String programme, Degree degree, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.programme = programme;
        this.degree = degree;
        this.email = email;
    }
}
