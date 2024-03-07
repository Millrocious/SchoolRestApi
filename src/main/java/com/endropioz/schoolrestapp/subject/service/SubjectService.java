package com.endropioz.schoolrestapp.subject.service;

import com.endropioz.schoolrestapp.subject.dto.SubjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SubjectService {

    Page<SubjectDto> getAllSubjects(Pageable pageable);

    SubjectDto getSubjectById(Long id);

    SubjectDto addSubject(SubjectDto subjectDto);

    SubjectDto updateSubjectById(Long id, SubjectDto requestBody);

    void deleteSubjectById(Long id);
}
