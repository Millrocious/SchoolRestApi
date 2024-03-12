package com.endropioz.schoolrestapp.subject.service.impl;

import com.endropioz.schoolrestapp.subject.dto.SubjectRequestDto;
import com.endropioz.schoolrestapp.subject.dto.SubjectResponseDto;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import com.endropioz.schoolrestapp.subject.mapper.SubjectMapper;
import com.endropioz.schoolrestapp.subject.repository.SubjectRepository;
import com.endropioz.schoolrestapp.subject.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectServiceImpl implements SubjectService {

    SubjectRepository subjectRepository;
    SubjectMapper subjectMapper = SubjectMapper.MAPPER;

    @Override
    public Page<SubjectResponseDto> getAllSubjects(Pageable pageable) {
        Page<Subject> subjects = subjectRepository.findAll(pageable);

        return subjects.map(subjectMapper::toResponseDto);
    }

    @Override
    public SubjectResponseDto getSubjectById(Long id) {
        return subjectMapper.toResponseDto(getExistingSubjectById(id));
    }

    @Override
    public SubjectResponseDto addSubject(SubjectRequestDto subjectDto) {
        Subject newSubject = subjectMapper.toEntity(subjectDto);

        return subjectMapper.toResponseDto(subjectRepository.save(newSubject));
    }

    @Override
    public SubjectResponseDto updateSubjectById(Long id, SubjectRequestDto subjectDto) {
        Subject existingSubject = getExistingSubjectById(id);

        subjectMapper.updateEntity(subjectDto, existingSubject);
        subjectRepository.save(existingSubject);

        return subjectMapper.toResponseDto(existingSubject);
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectRepository.findById(id).ifPresent(subjectRepository::delete);
    }

    private Subject getExistingSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));
    }
}
