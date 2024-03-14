package com.endropioz.schoolrestapp.module.service.impl;

import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import com.endropioz.schoolrestapp.classgroup.repository.ClassGroupRepository;
import com.endropioz.schoolrestapp.module.dto.ModuleSubjectClassTeacherResponseDto;
import com.endropioz.schoolrestapp.module.entity.Module;
import com.endropioz.schoolrestapp.module.entity.ModuleSubjectClassTeacher;
import com.endropioz.schoolrestapp.module.entity.ModuleSubjectClassTeacherKey;
import com.endropioz.schoolrestapp.module.mapper.ModuleMapper;
import com.endropioz.schoolrestapp.module.repository.ModuleRepository;
import com.endropioz.schoolrestapp.module.repository.ModuleSubjectClassTeacherRepository;
import com.endropioz.schoolrestapp.module.service.ModuleSubjectClassTeacherService;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import com.endropioz.schoolrestapp.subject.repository.SubjectRepository;
import com.endropioz.schoolrestapp.teacher.entity.Teacher;
import com.endropioz.schoolrestapp.teacher.repository.TeacherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ModuleSubjectClassTeacherServiceImpl implements ModuleSubjectClassTeacherService {
    ModuleSubjectClassTeacherRepository moduleSubjectClassTeacherRepository;
    ModuleRepository moduleRepository;
    TeacherRepository teacherRepository;
    SubjectRepository subjectRepository;
    ClassGroupRepository classGroupRepository;
    ModuleMapper moduleMapper = ModuleMapper.MAPPER;

    @Override
    public Page<ModuleSubjectClassTeacherResponseDto> getAllModuleSubjectClassTeacherByModuleId(Long teacherId, Pageable pageable) {
        Page<ModuleSubjectClassTeacher> teacherSubjectClasses = moduleSubjectClassTeacherRepository.findAllByModuleId(teacherId, pageable);

        return teacherSubjectClasses.map(moduleMapper::toResponseDto);
    }

    @Override
    @Transactional
    public void addNewModuleSubjectClassTeacher(Long moduleId, Long teacherId, Long subjectId, Long classGroupId) {
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Module not found"));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));

        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

        ModuleSubjectClassTeacherKey moduleSubjectClassTeacherKey = new ModuleSubjectClassTeacherKey();
        moduleSubjectClassTeacherKey.setModuleId(moduleId);
        moduleSubjectClassTeacherKey.setSubjectId(subjectId);
        moduleSubjectClassTeacherKey.setClassGroupId(classGroupId);
        moduleSubjectClassTeacherKey.setTeacherId(teacherId);

        ModuleSubjectClassTeacher moduleSubjectClassTeacher = new ModuleSubjectClassTeacher();
        moduleSubjectClassTeacher.setId(moduleSubjectClassTeacherKey);
        moduleSubjectClassTeacher.setModule(module);
        moduleSubjectClassTeacher.setSubject(subject);
        moduleSubjectClassTeacher.setClassGroup(classGroup);
        moduleSubjectClassTeacher.setTeacher(teacher);

        moduleSubjectClassTeacherRepository.save(moduleSubjectClassTeacher);
    }

    @Override
    public void deleteModuleSubjectClassTeacher(Long moduleId, Long subjectId, Long classGroupId, Long teacherId) {
        moduleSubjectClassTeacherRepository
                .findByModuleIdAndSubjectIdAndClassGroupIdAndTeacherId(moduleId, subjectId, classGroupId, teacherId)
                .ifPresent(moduleSubjectClassTeacherRepository::delete);
    }
}
