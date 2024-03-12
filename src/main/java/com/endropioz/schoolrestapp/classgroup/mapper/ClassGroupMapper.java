package com.endropioz.schoolrestapp.classgroup.mapper;

import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupRequestDto;
import com.endropioz.schoolrestapp.classgroup.dto.ClassGroupResponseDto;
import com.endropioz.schoolrestapp.classgroup.entity.ClassGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClassGroupMapper {

    ClassGroupMapper MAPPER = Mappers.getMapper(ClassGroupMapper.class);

    ClassGroupResponseDto toResponseDto(ClassGroup classGroup);

    ClassGroup toEntity(ClassGroupRequestDto classGroupRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ClassGroupRequestDto classGroupRequestDto, @MappingTarget ClassGroup classGroup);
}
