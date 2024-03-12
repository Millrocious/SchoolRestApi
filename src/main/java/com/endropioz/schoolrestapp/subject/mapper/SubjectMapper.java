package com.endropioz.schoolrestapp.subject.mapper;

import com.endropioz.schoolrestapp.subject.dto.SubjectRequestDto;
import com.endropioz.schoolrestapp.subject.dto.SubjectResponseDto;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SubjectMapper {

    SubjectMapper MAPPER = Mappers.getMapper(SubjectMapper.class);

    SubjectResponseDto toResponseDto(Subject subject);

    Subject toEntity(SubjectRequestDto studentRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(SubjectRequestDto subjectDto, @MappingTarget Subject subject);
}
