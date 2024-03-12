package com.endropioz.schoolrestapp.teacher.service.impl;

import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import com.endropioz.schoolrestapp.classgroup.repository.ClassGroupRepository;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import com.endropioz.schoolrestapp.subject.repository.SubjectRepository;
import com.endropioz.schoolrestapp.teacher.dto.TeacherSubjectClassResponseDto;
import com.endropioz.schoolrestapp.teacher.entity.Teacher;
import com.endropioz.schoolrestapp.teacher.entity.TeacherSubjectClass;
import com.endropioz.schoolrestapp.teacher.entity.TeacherSubjectClassKey;
import com.endropioz.schoolrestapp.teacher.mapper.TeacherMapper;
import com.endropioz.schoolrestapp.teacher.repository.TeacherRepository;
import com.endropioz.schoolrestapp.teacher.repository.TeacherSubjectClassRepository;
import com.endropioz.schoolrestapp.teacher.service.TeacherSubjectClassService;
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
public class TeacherSubjectClassServiceImpl implements TeacherSubjectClassService {

    TeacherRepository teacherRepository;
    TeacherSubjectClassRepository teacherSubjectClassRepository;
    SubjectRepository subjectRepository;
    ClassGroupRepository classGroupRepository;

    TeacherMapper teacherMapper = TeacherMapper.MAPPER;

    @Override
    public Page<TeacherSubjectClassResponseDto> getAllTeacherSubjectClasses(Pageable pageable) {
        Page<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassRepository.findAll(pageable);

        return teacherSubjectClasses.map(teacherMapper::toResponseDto);
    }

    @Override
    public Page<TeacherSubjectClassResponseDto> getAllTeacherSubjectClassesByTeacherId(Long teacherId, Pageable pageable) {
        Page<TeacherSubjectClass> teacherSubjectClasses = teacherSubjectClassRepository.findAllByTeacherId(teacherId, pageable);

        return teacherSubjectClasses.map(teacherMapper::toResponseDto);
    }

    @Override
    @Transactional
    public void addSubjectClassToTeacher(Long teacherId, Long subjectId, Long classGroupId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found"));

        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

        TeacherSubjectClassKey teacherSubjectClassKey = new TeacherSubjectClassKey();
        teacherSubjectClassKey.setClassGroupId(classGroupId);
        teacherSubjectClassKey.setSubjectId(subjectId);
        teacherSubjectClassKey.setTeacherId(teacherId);

        TeacherSubjectClass teacherSubjectClass = new TeacherSubjectClass();
        teacherSubjectClass.setId(teacherSubjectClassKey);
        teacherSubjectClass.setClassGroup(classGroup);
        teacherSubjectClass.setSubject(subject);
        teacherSubjectClass.setTeacher(teacher);

        teacherSubjectClassRepository.save(teacherSubjectClass);
    }

    @Override
    public void deleteTeacherSubjectClass(Long teacherId, Long subjectId, Long classGroupId) {
        teacherSubjectClassRepository
                .findByTeacherIdAndSubjectIdAndClassGroupId(teacherId, subjectId, classGroupId)
                .ifPresent(teacherSubjectClassRepository::delete);
    }
}
