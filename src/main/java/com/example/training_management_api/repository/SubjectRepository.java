package com.example.training_management_api.repository;

import com.example.training_management_api.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
