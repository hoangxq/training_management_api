package com.example.training_management_api.repository;

import com.example.training_management_api.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
