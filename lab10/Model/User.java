package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "LENGTH(name) > 4 AND age>21 AND role='job_seeker' OR role='employer'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "The name should not be empty")
    @Size(min = 4,message = "The length must be more than 4 characters")
    @Pattern(regexp = "^[A-Za-z]+$",message = "The name should have only characters")
    private String name;

    @Column(unique = true, nullable = false)
    @Email(message = "The email should be in the right form")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "The password should not be empty")
    private String password;

    @Column(nullable = false)
    @Min(value = 22, message = "The age should be at least 21")
    private Integer age;

    @Column(nullable = false)
    @NotEmpty(message = "The role should not be empty")
    @Pattern(regexp = "job_seeker|employer")
    private String role;
}
