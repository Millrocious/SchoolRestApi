package com.endropioz.schoolrestapp.lesson.controller;

import com.endropioz.schoolrestapp.lesson.dto.LessonRequestDto;
import com.endropioz.schoolrestapp.lesson.dto.LessonResponseDto;
import com.endropioz.schoolrestapp.lesson.service.LessonService;
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
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonController {
    LessonService lessonService;

    @GetMapping("/subjects/{subjectId}")
    public Page<LessonResponseDto> getAllLessonsBySubjectId(
            @PathVariable Long subjectId,
            @PageableDefault Pageable pageable
    ) {
        return lessonService.findAllBySubjectId(subjectId, pageable);
    }

    @GetMapping("/modules/{moduleId}")
    public Page<LessonResponseDto> getAllLessonsByModuleId(
            @PathVariable Long moduleId,
            @PageableDefault Pageable pageable
    ) {
        return lessonService.findAllByModuleId(moduleId, pageable);
    }

    @GetMapping("/{id}")
    public LessonResponseDto getLesson(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonResponseDto createLesson(@RequestBody @Valid LessonRequestDto lessonDto) {
        return lessonService.addNewLesson(lessonDto);
    }

    @PutMapping("/{id}")
    public LessonResponseDto updateLesson(
            @PathVariable Long id,
            @RequestBody @Valid LessonRequestDto lessonDto
    ) {
        return lessonService.updateLessonById(id, lessonDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
    }
}
