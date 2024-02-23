package com.example.training_management_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course_supper_visors")
public class CourseSupperVisor extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "supper_visor_user_id")
    private User supperVisorUser;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}