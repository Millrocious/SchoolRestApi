package com.endropioz.schoolrestapp.subject.mapper;

import com.endropioz.schoolrestapp.subject.dto.SubjectDto;
import com.endropioz.schoolrestapp.subject.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SubjectMapper {

    SubjectMapper MAPPER = Mappers.getMapper(SubjectMapper.class);

    SubjectDto toSubjectDto(Subject subject);

    Subject toEntity(SubjectDto studentRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(SubjectDto subjectDto, @MappingTarget Subject subject);
}
