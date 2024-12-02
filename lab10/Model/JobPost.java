package com.example.lab10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "LENGTH(title) > 4 AND salary>0")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "The title should not be empty")
    @Size(min = 5,message = "The title length must be more than 4 characters")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "The description should not be empty")
    private String description;

    @Column(nullable = false)
    @NotEmpty(message = "The location should not be empty")
    private String location;

    @Column(nullable = false)
    @NotNull(message = "The salary should have a value")
    @Positive(message = "The salary should be a positive number")
    private Integer salary;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postingDate;

}
