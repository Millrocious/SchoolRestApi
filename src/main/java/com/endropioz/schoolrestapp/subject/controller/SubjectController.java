package com.endropioz.schoolrestapp.subject.controller;

import com.endropioz.schoolrestapp.subject.dto.SubjectDto;
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
    public Page<SubjectDto> getAllSubjects(@PageableDefault Pageable pageable) {
        return subjectService.getAllSubjects(pageable);
    }

    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable("id") Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDto addNewSubject(@RequestBody @Valid SubjectDto subjectDto) {
        return subjectService.addSubject(subjectDto);
    }

    @PutMapping("/{id}")
    public SubjectDto updateSubjectById(@PathVariable("id") Long id, @RequestBody @Valid SubjectDto requestBody) {
        return subjectService.updateSubjectById(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteSubjectById(@PathVariable("id") Long id) {
        subjectService.deleteSubjectById(id);
    }
}
