package com.endropioz.schoolrestapp.lesson.service;

import com.endropioz.schoolrestapp.lesson.dto.LessonRequestDto;
import com.endropioz.schoolrestapp.lesson.dto.LessonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {
    LessonResponseDto getLessonById(Long id);
    LessonResponseDto addNewLesson(LessonRequestDto dto);
    LessonResponseDto updateLessonById(Long id, LessonRequestDto dto);
    void deleteLessonById(Long id);
    Page<LessonResponseDto> findAllBySubjectId(Long subjectId, Pageable pageable);
    Page<LessonResponseDto> findAllByModuleId(Long moduleId, Pageable pageable);
}
