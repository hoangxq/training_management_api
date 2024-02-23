package com.example.training_management_api.model;

import com.example.training_management_api.model.constants.CourseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "courses")
public class Course extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "finish_date")
    private Date finishDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CourseStatus status;
}