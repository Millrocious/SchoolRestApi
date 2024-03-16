package com.endropioz.schoolrestapp.classgroup.service.impl;

import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupRequestDto;
import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupResponseDto;
import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import com.endropioz.schoolrestapp.classgroup.mapper.ClassGroupMapper;
import com.endropioz.schoolrestapp.classgroup.repository.ClassGroupRepository;
import com.endropioz.schoolrestapp.classgroup.service.ClassGroupService;
import com.endropioz.schoolrestapp.student.entity.Student;
import com.endropioz.schoolrestapp.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassGroupServiceImpl implements ClassGroupService {

    ClassGroupRepository classGroupRepository;
    StudentRepository studentRepository;

    ClassGroupMapper classGroupMapper = ClassGroupMapper.MAPPER;

    @Override
    @Transactional
    public ClassGroupResponseDto createClassGroup(ClassGroupRequestDto classGroupDto) {
        ClassGroup newClassGroup = classGroupMapper.toEntity(classGroupDto);

        return classGroupMapper.toResponseDto(classGroupRepository.save(newClassGroup));
    }

    @Override
    @Transactional
    public ClassGroupResponseDto getClassGroupById(Long id) {
        return classGroupMapper.toResponseDto(findById(id));
    }

    @Override
    @Transactional
    public Page<ClassGroupResponseDto> getAllClassGroups(Pageable pageable) {
        return classGroupRepository.findAll(pageable)
                .map(classGroupMapper::toResponseDto);
    }

    @Override
    @Transactional
    public void deleteClassGroupById(Long id) {
        classGroupRepository.findById(id).ifPresent(classGroupRepository::delete);
    }

    @Override
    @Transactional
    public ClassGroupResponseDto updateClassGroup(Long id, ClassGroupRequestDto classGroupDto) {
        ClassGroup existingClassGroup = findById(id);

        classGroupMapper.updateEntity(classGroupDto, existingClassGroup);

        return classGroupMapper.toResponseDto(classGroupRepository.save(existingClassGroup));
    }

    @Override
    public void addStudentToClassGroup(Long classGroupId, Long studentId) {
        ClassGroup existingClassGroup = findById(classGroupId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        existingClassGroup.getStudents().add(student);

        classGroupRepository.save(existingClassGroup);
    }

    private ClassGroup findById(Long id) {
        return classGroupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ClassGroup not found"));
    }
}
