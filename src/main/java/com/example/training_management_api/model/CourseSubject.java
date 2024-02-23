package com.example.training_management_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course_subjects")
public class CourseSubject extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}