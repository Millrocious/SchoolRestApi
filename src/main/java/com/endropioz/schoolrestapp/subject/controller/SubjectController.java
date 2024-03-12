package com.endropioz.schoolrestapp.subject.controller;

import com.endropioz.schoolrestapp.subject.dto.SubjectRequestDto;
import com.endropioz.schoolrestapp.subject.dto.SubjectResponseDto;
import com.endropioz.schoolrestapp.subject.service.SubjectService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubjectController {

    SubjectService subjectService;

    @GetMapping
    public Page<SubjectResponseDto> getAllSubjects(@PageableDefault Pageable pageable) {
        return subjectService.getAllSubjects(pageable);
    }

    @GetMapping("/{id}")
    public SubjectResponseDto getSubjectById(@PathVariable("id") Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponseDto addNewSubject(@RequestBody @Valid SubjectRequestDto subjectDto) {
        return subjectService.addSubject(subjectDto);
    }

    @PutMapping("/{id}")
    public SubjectResponseDto updateSubjectById(@PathVariable("id") Long id, @RequestBody @Valid SubjectRequestDto requestBody) {
        return subjectService.updateSubjectById(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteSubjectById(@PathVariable("id") Long id) {
        subjectService.deleteSubjectById(id);
    }
}
