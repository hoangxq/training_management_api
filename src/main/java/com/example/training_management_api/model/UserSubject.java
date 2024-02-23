package com.example.training_management_api.model;

import com.example.training_management_api.model.constants.TraineeSubjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user_subjects")
public class UserSubject extends AbstractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "course_trainee_id")
    private CourseTrainee courseTrainee;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "finish_time")
    private Timestamp finishTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "trainee_subject_status")
    private TraineeSubjectStatus traineeSubjectStatus;
}