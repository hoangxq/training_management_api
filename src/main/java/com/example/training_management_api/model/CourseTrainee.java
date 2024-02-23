package com.example.training_management_api.model;

import com.example.training_management_api.model.constants.CourseTraineeResultStatus;
import com.example.training_management_api.model.constants.CourseTraineeStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course_trainees")
public class CourseTrainee extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trainee_user_id")
    private User traineeUser;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Enumerated(EnumType.STRING)
    @Column(name = "result_status")
    private CourseTraineeResultStatus resultStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "course_trainee_status")
    private CourseTraineeStatus courseTraineeStatus;
}