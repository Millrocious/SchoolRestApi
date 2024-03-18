package com.endropioz.schoolrestapp.grade.controller;

import com.endropioz.schoolrestapp.grade.dto.GradeRequestDto;
import com.endropioz.schoolrestapp.grade.dto.GradeResponseDto;
import com.endropioz.schoolrestapp.grade.service.GradeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grades")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GradeController {

    GradeService gradeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public GradeResponseDto addGrade(@RequestBody @Valid GradeRequestDto gradeDto) {
        return gradeService.addGrade(gradeDto);
    }

    @GetMapping("/{gradeId}")
    public GradeResponseDto getGradeById(@PathVariable Long gradeId) {
        return gradeService.getGradeById(gradeId);
    }

    @GetMapping("/students/{studentId}")
    public Page<GradeResponseDto> getAllGradesByStudentId(
            @PathVariable Long studentId,
            Pageable pageable
    ) {
        return gradeService.getGradesByStudentId(studentId, pageable);
    }

    @GetMapping("/lessons/{lessonId}")
    public Page<GradeResponseDto> getAllGradesByLessonId(
            @PathVariable Long lessonId,
            Pageable pageable
    ) {
        return gradeService.getGradesByLessonId(lessonId, pageable);
    }

    @GetMapping("/modules/{moduleId}")
    public Page<GradeResponseDto> getAllGradesByModuleId(
            @PathVariable Long moduleId,
            Pageable pageable
    ) {
        return gradeService.getGradesByModuleId(moduleId, pageable);
    }

    @GetMapping("/modules/{moduleId}/students/{studentId}")
    public Page<GradeResponseDto> getAllGradesByModuleIdAndStudentId(
            @PathVariable Long moduleId,
            @PathVariable Long studentId,
            Pageable pageable
    ) {
        return gradeService.getGradesByModuleIdAndStudentId(moduleId, studentId, pageable);
    }

    @GetMapping("/lessons/{lessonId}/students/{studentId}")
    public Page<GradeResponseDto> getAllGradesByLessonIdAndStudentId(
            @PathVariable Long lessonId,
            @PathVariable Long studentId,
            Pageable pageable
    ) {
        return gradeService.getGradesByLessonIdAndStudentId(lessonId, studentId, pageable);
    }

    @PutMapping("/{gradeId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public GradeResponseDto updateGrade(
            @RequestBody @Valid GradeRequestDto gradeDto,
            @PathVariable Long gradeId
    ) {
        return gradeService.updateGrade(gradeId, gradeDto);
    }

    @DeleteMapping("/{gradeId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public void deleteGrade(@PathVariable Long gradeId) {
        gradeService.deleteGrade(gradeId);
    }
}
