package com.endropioz.schoolrestapp.teacher.mapper;

import com.endropioz.schoolrestapp.teacher.dto.TeacherRequestDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherResponseDto;
import com.endropioz.schoolrestapp.teacher.dto.TeacherSubjectClassResponseDto;
import com.endropioz.schoolrestapp.teacher.entity.Teacher;
import com.endropioz.schoolrestapp.teacher.entity.TeacherSubjectClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {

    TeacherMapper MAPPER = Mappers.getMapper(TeacherMapper.class);

    TeacherResponseDto toResponseDto(Teacher teacher);

    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "classGroup", target = "classGroup")
    TeacherSubjectClassResponseDto toResponseDto(TeacherSubjectClass teacher);

    Teacher toEntity(TeacherRequestDto teacherRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(TeacherRequestDto teacherRequestDto, @MappingTarget Teacher teacher);
}
