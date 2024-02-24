package com.example.training_management_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectRequest {
    @NotBlank
    private String name;
    private String description;
}