package com.endropioz.schoolrestapp.module.controller;

import com.endropioz.schoolrestapp.module.dto.ModuleRequestDto;
import com.endropioz.schoolrestapp.module.dto.ModuleResponseDto;
import com.endropioz.schoolrestapp.module.dto.ModuleSubjectClassTeacherResponseDto;
import com.endropioz.schoolrestapp.module.service.ModuleService;
import com.endropioz.schoolrestapp.module.service.ModuleSubjectClassTeacherService;
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
@RequestMapping("/api/modules")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ModuleController {
    ModuleService moduleService;
    ModuleSubjectClassTeacherService moduleSubjectClassTeacherService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public Page<ModuleResponseDto> getAllModules(@PageableDefault Pageable pageable) {
        return moduleService.getAllModules(pageable);
    }

    @GetMapping("/{moduleId}/subject-class-teachers")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public Page<ModuleSubjectClassTeacherResponseDto> getAllModuleSubjectClassTeachersByModuleId(
            @PathVariable("moduleId") Long moduleId,
            @PageableDefault Pageable pageable
    ) {
        return moduleSubjectClassTeacherService.getAllModuleSubjectClassTeacherByModuleId(moduleId, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public ModuleResponseDto getModuleById(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public ModuleResponseDto addNewModule(@RequestBody @Valid ModuleRequestDto moduleDto) {
        return moduleService.addNewModule(moduleDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public ModuleResponseDto updateModuleById(
            @PathVariable("id") Long id,
            @RequestBody @Valid ModuleRequestDto moduleDto
    ){
        return moduleService.updateModuleById(id, moduleDto);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    @PutMapping("/{moduleId}/subjects/{subjectId}/classes/{classId}/teachers/{teacherId}")
    public void addNewModuleSubjectClassTeacher(
            @PathVariable("moduleId") Long moduleId,
            @PathVariable("subjectId") Long subjectId,
            @PathVariable("classId") Long classId,
            @PathVariable("teacherId") Long teacherId
    ){
        moduleSubjectClassTeacherService.addNewModuleSubjectClassTeacher(moduleId, subjectId, classId, teacherId);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    @DeleteMapping("/{moduleId}/subjects/{subjectId}/classes/{classId}/teachers/{teacherId}")
    public void deleteModuleSubjectClassTeacher(
            @PathVariable("moduleId") Long moduleId,
            @PathVariable("subjectId") Long subjectId,
            @PathVariable("classId") Long classId,
            @PathVariable("teacherId") Long teacherId
    ){
        moduleSubjectClassTeacherService.deleteModuleSubjectClassTeacher(moduleId, subjectId, classId, teacherId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'SCHOOL_ADMIN', 'TEACHER')")
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }

}
