package com.endropioz.schoolrestapp.lesson.service.impl;

import com.endropioz.schoolrestapp.lesson.dto.LessonRequestDto;
import com.endropioz.schoolrestapp.lesson.dto.LessonResponseDto;
import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import com.endropioz.schoolrestapp.lesson.mapper.LessonMapper;
import com.endropioz.schoolrestapp.lesson.repository.LessonRepository;
import com.endropioz.schoolrestapp.lesson.service.LessonService;
import com.endropioz.schoolrestapp.module.entity.Module;
import com.endropioz.schoolrestapp.module.repository.ModuleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonServiceImpl implements LessonService {
    LessonRepository lessonRepository;
    ModuleRepository moduleRepository;
    LessonMapper lessonMapper = LessonMapper.MAPPER;

    @Override
    @Transactional
    public LessonResponseDto addNewLesson(LessonRequestDto dto) {
        Lesson lesson = lessonMapper.toEntity(dto);

        Module module = moduleRepository.findById(dto.moduleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found"));

        lesson.setModule(module);

        return lessonMapper.toResponseDto(lessonRepository.save(lesson));
    }

    @Override
    @Transactional(readOnly = true)
    public LessonResponseDto getLessonById(Long id) {
        Lesson lesson = getExistingLessonById(id);

        return lessonMapper.toResponseDto(lesson);
    }

    @Override
    @Transactional
    public LessonResponseDto updateLessonById(Long id, LessonRequestDto dto) {
        Lesson lesson = getExistingLessonById(id);

        lessonMapper.updateEntity(dto, lesson);
        lessonRepository.save(lesson);

        return lessonMapper.toResponseDto(lesson);
    }

    @Override
    @Transactional
    public void deleteLessonById(Long id) {
        lessonRepository.findById(id).ifPresent(lessonRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LessonResponseDto> findAllBySubjectId(Long subjectId, Pageable pageable) {
        Page<Lesson> lessons = lessonRepository.findAllBySubjectId(subjectId, pageable);

        return lessons.map(lessonMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LessonResponseDto> findAllByModuleId(Long moduleId, Pageable pageable) {
        Page<Lesson> lessons = lessonRepository.findAllByModuleId(moduleId, pageable);

        return lessons.map(lessonMapper::toResponseDto);
    }

    private Lesson getExistingLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }
}