package com.endropioz.schoolrestapp.module.service;

import com.endropioz.schoolrestapp.module.dto.ModuleSubjectClassTeacherResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModuleSubjectClassTeacherService {
    Page<ModuleSubjectClassTeacherResponseDto> getAllModuleSubjectClassTeacherByModuleId(Long moduleId, Pageable pageable);

    void addNewModuleSubjectClassTeacher(Long moduleId, Long subjectId, Long classId, Long teacherId);

    void deleteModuleSubjectClassTeacher(Long moduleId, Long subjectId, Long classId, Long teacherId);
}
