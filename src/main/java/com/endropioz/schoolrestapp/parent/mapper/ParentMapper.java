package com.endropioz.schoolrestapp.parent.mapper;

import com.endropioz.schoolrestapp.parent.dto.ParentRequestDto;
import com.endropioz.schoolrestapp.parent.dto.ParentResponseDto;
import com.endropioz.schoolrestapp.parent.entity.Parent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParentMapper {
    ParentMapper MAPPER = Mappers.getMapper(ParentMapper.class);

    ParentResponseDto toResponseDto(Parent parent);

    Parent toEntity(ParentRequestDto parentRequestDto);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ParentRequestDto parentRequestDto, @MappingTarget Parent parent);
}
