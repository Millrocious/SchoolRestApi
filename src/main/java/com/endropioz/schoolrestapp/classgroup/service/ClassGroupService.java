package com.endropioz.schoolrestapp.classgroup.service;

import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupRequestDto;
import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassGroupService {

  ClassGroupResponseDto createClassGroup(ClassGroupRequestDto classGroupDto);

  ClassGroupResponseDto getClassGroupById(Long id);

  Page<ClassGroupResponseDto> getAllClassGroups(Pageable pageable);

  void deleteClassGroupById(Long id);

  ClassGroupResponseDto updateClassGroup(Long id, ClassGroupRequestDto classGroupDto);

  void addStudentToClassGroup(Long classGroupId, Long studentId);
}
