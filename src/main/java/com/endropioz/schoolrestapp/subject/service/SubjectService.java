package com.endropioz.schoolrestapp.subject.service;

import com.endropioz.schoolrestapp.subject.dto.SubjectRequestDto;
import com.endropioz.schoolrestapp.subject.dto.SubjectResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SubjectService {

    Page<SubjectResponseDto> getAllSubjects(Pageable pageable);

    SubjectResponseDto getSubjectById(Long id);

    SubjectResponseDto addSubject(SubjectRequestDto subjectDto);

    SubjectResponseDto updateSubjectById(Long id, SubjectRequestDto requestBody);

    void deleteSubjectById(Long id);
}
