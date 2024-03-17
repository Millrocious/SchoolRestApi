package com.endropioz.schoolrestapp.grade.service;

import com.endropioz.schoolrestapp.grade.dto.GradeRequestDto;
import com.endropioz.schoolrestapp.grade.dto.GradeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

public interface GradeService {
    @Transactional
    GradeResponseDto addGrade(GradeRequestDto gradeDto);

    @Transactional(readOnly = true)
    GradeResponseDto getGradeById(Long gradeId);

    @Transactional(readOnly = true)
    Page<GradeResponseDto> getGradesByStudentId(Long studentId, Pageable pageable);

    @Transactional(readOnly = true)
    Page<GradeResponseDto> getGradesByLessonId(Long lessonId, Pageable pageable);

    @Transactional(readOnly = true)
    Page<GradeResponseDto> getGradesByModuleId(Long moduleId, Pageable pageable);

    @Transactional(readOnly = true)
    Page<GradeResponseDto> getGradesByModuleIdAndStudentId(Long moduleId, Long studentId, Pageable pageable);

    @Transactional(readOnly = true)
    Page<GradeResponseDto> getGradesByLessonIdAndStudentId(Long lessonId, Long studentId, Pageable pageable);

    @Transactional(readOnly = true)
    List<GradeResponseDto> getGradesByStudentIdAndDate(Long studentId, ZonedDateTime date);

    @Transactional
    GradeResponseDto updateGrade(Long gradeId, GradeRequestDto gradeDto);

    @Transactional
    void deleteGrade(Long gradeId);
}
