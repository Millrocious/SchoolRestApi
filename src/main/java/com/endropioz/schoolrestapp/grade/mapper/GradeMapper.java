package com.endropioz.schoolrestapp.grade.mapper;

import com.endropioz.schoolrestapp.grade.dto.GradeRequestDto;
import com.endropioz.schoolrestapp.grade.dto.GradeResponseDto;
import com.endropioz.schoolrestapp.grade.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeMapper {
    GradeMapper MAPPER = Mappers.getMapper(GradeMapper.class);

    @Mapping(source = "lesson", target = "lesson")
    @Mapping(source = "student", target = "student")
    GradeResponseDto toResponseDto(Grade grade);

    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    Grade toEntity(GradeRequestDto gradeRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updateEntity(GradeRequestDto gradeRequestDto, @MappingTarget Grade grade);
}
