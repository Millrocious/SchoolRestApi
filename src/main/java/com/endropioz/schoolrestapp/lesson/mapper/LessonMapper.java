package com.endropioz.schoolrestapp.lesson.mapper;

import com.endropioz.schoolrestapp.lesson.dto.LessonRequestDto;
import com.endropioz.schoolrestapp.lesson.dto.LessonResponseDto;
import com.endropioz.schoolrestapp.lesson.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessonMapper {
    LessonMapper MAPPER = Mappers.getMapper(LessonMapper.class);

    LessonResponseDto toResponseDto(Lesson lesson);

    Lesson toEntity(LessonRequestDto dto);

    void updateEntity(LessonRequestDto dto, @MappingTarget Lesson lesson);

    List<Lesson> listOfLessonDtosToListOfLessons(List<LessonRequestDto> lessonDtos);
}
