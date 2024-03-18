package com.endropioz.schoolrestapp.teacher.controller;

import com.endropioz.schoolrestapp.teacher.dto.TeacherRequestDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherResponseDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherSubjectClassResponseDto;
import com.endropioz.schoolrestapp.teacher.service.TeacherService;
import com.endropioz.schoolrestapp.teacher.service.TeacherSubjectClassService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherController {

    TeacherService teacherService;
    TeacherSubjectClassService teacherSubjectClassService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public Page<TeacherResponseDto> getAllTeachers(@PageableDefault Pageable pageable) {
        return teacherService.getAllTeachers(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public TeacherResponseDto getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public TeacherResponseDto addNewTeacher(@RequestBody @Valid TeacherRequestDto teacherDto) {
        return teacherService.addNewTeacher(teacherDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public TeacherResponseDto updateTeacherById(
            @PathVariable Long id,
            @RequestBody @Valid TeacherRequestDto teacherDto
    ) {
        return teacherService.updateTeacherById(id, teacherDto);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    @PutMapping("/{teacherId}/subjects/{subjectId}/classes/{classGroupId}")
    public void addSubjectToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @PathVariable Long classGroupId
    ) {
        teacherSubjectClassService.addSubjectClassToTeacher(teacherId, subjectId, classGroupId);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    @DeleteMapping("/{teacherId}/subjects/{subjectId}/classes/{classGroupId}")
    public void deleteSubjectToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long subjectId,
            @PathVariable Long classGroupId
    ) {
        teacherSubjectClassService.deleteTeacherSubjectClass(teacherId, subjectId, classGroupId);
    }

    @GetMapping("/{teacherId}/subject-classes")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public Page<TeacherSubjectClassResponseDto> getTeacherSubjectClasses(
            @PathVariable Long teacherId,
            @PageableDefault Pageable pageable
    ) {
        return teacherSubjectClassService.getAllTeacherSubjectClassesByTeacherId(teacherId, pageable);
    }

    @GetMapping("/subject-classes")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public Page<TeacherSubjectClassResponseDto> getAllTeacherSubjectClasses(
            @PageableDefault Pageable pageable
    ) {
        return teacherSubjectClassService.getAllTeacherSubjectClasses(pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN')")
    public void deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
    }
}
