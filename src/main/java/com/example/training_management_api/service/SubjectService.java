package com.example.training_management_api.service;

import com.example.training_management_api.dto.SubjectDto;
import com.example.training_management_api.dto.request.SubjectRequest;
import com.example.training_management_api.dto.utils.PagingRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {
    PagingRes getListSubject(Pageable pageable);

    SubjectDto getSubjectById(Integer subjectId);

    SubjectDto createSubject(SubjectRequest subjectRequest);

    SubjectDto updateSubject(Integer subjectId, SubjectRequest subjectRequest);

    Integer deleteSubject(Integer subjectId);
}