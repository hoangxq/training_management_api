package com.example.training_management_api.service.impl;

import com.example.training_management_api.dto.SubjectDto;
import com.example.training_management_api.dto.request.SubjectRequest;
import com.example.training_management_api.dto.utils.PagingRes;
import com.example.training_management_api.exception.CommonException;
import com.example.training_management_api.exception.EntityNotFoundException;
import com.example.training_management_api.exception.common.ServiceError;
import com.example.training_management_api.model.Subject;
import com.example.training_management_api.repository.SubjectRepository;
import com.example.training_management_api.repository.UserRepository;
import com.example.training_management_api.security.SecurityUtils;
import com.example.training_management_api.service.SubjectService;
import com.example.training_management_api.service.component.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final MappingHelper mappingHelper;
    private final SecurityUtils securityUtils;

    @Override
    public PagingRes getListSubject(Pageable pageable) {
        var subjects = subjectRepository.findAll(pageable);
        return PagingRes.of(mappingHelper.mapPage(subjects, SubjectDto.class));
    }

    @Override
    public SubjectDto getSubjectById(Integer subjectId) {
        return subjectRepository
                .findById(subjectId)
                .map((element) -> mappingHelper.map(element, SubjectDto.class))
                .orElseThrow(() -> new EntityNotFoundException(Subject.class.toString(), subjectId.toString()));
    }

    @Override
    @Transactional
    public SubjectDto createSubject(SubjectRequest subjectRequest) {
        var subject = mappingHelper.map(subjectRequest, Subject.class);
        subjectRepository.save(subject);
        return mappingHelper.map(subject, SubjectDto.class);
    }

    @Override
    @Transactional
    public SubjectDto updateSubject(Integer subjectId, SubjectRequest subjectRequest) {
        var subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException(Subject.class.toString(), subjectId.toString()));
        mappingHelper.mapIfSourceNotNullAndStringNotBlank(subjectRequest, subject);
        subjectRepository.save(subject);
        return mappingHelper.map(subject, SubjectDto.class);
    }

    @Override
    @Transactional
    public Integer deleteSubject(Integer subjectId) {
        var subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException(Subject.class.toString(), subjectId.toString()));
        subject.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        var currentUser = securityUtils.getCurrentUserJwt()
                .orElseThrow(() -> new CommonException(ServiceError.CURRENT_USER_NOT_FOUND));
        subject.setDeletedBy(currentUser);
        subjectRepository.save(subject);
        return subjectId;
    }
}