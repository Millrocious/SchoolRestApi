package com.endropioz.schoolrestapp.student.mapper;

import com.endropioz.schoolrestapp.student.dto.StudentRequestDto;
import com.endropioz.schoolrestapp.student.dto.StudentResponseDto;
import com.endropioz.schoolrestapp.student.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    StudentResponseDto toResponseDto(Student student);

    Student toEntity(StudentRequestDto studentRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(StudentRequestDto studentRequestDto, @MappingTarget Student student);
}
