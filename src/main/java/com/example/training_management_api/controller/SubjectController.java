package com.example.training_management_api.controller;

import com.example.training_management_api.dto.request.SubjectRequest;
import com.example.training_management_api.dto.utils.PagingReq;
import com.example.training_management_api.dto.utils.ResponseUtils;
import com.example.training_management_api.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<?> getListSubject(@Validated PagingReq pagingReq) {
        return ResponseUtils.ok(subjectService.getListSubject(pagingReq.makePageable()));
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<?> getSubjectById(@PathVariable Integer subjectId) {
        return ResponseUtils.ok(subjectService.getSubjectById(subjectId));
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody @Validated SubjectRequest subjectRequest) {
        return ResponseUtils.ok(subjectService.createSubject(subjectRequest));
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<?> updateSubject(@RequestBody @Validated SubjectRequest subjectRequest, @PathVariable Integer subjectId) {
        return ResponseUtils.ok(subjectService.updateSubject(subjectId, subjectRequest));
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer subjectId) {
        return ResponseUtils.ok(subjectService.deleteSubject(subjectId));
    }
}