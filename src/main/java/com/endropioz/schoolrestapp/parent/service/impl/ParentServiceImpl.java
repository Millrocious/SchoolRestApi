package com.endropioz.schoolrestapp.parent.service.impl;

import com.endropioz.schoolrestapp.parent.dto.ParentRequestDto;
import com.endropioz.schoolrestapp.parent.dto.ParentResponseDto;
import com.endropioz.schoolrestapp.parent.entity.Parent;
import com.endropioz.schoolrestapp.parent.mapper.ParentMapper;
import com.endropioz.schoolrestapp.parent.repository.ParentRepository;
import com.endropioz.schoolrestapp.parent.service.ParentService;
import com.endropioz.schoolrestapp.student.repository.StudentRepository;
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
public class ParentServiceImpl implements ParentService {
    ParentRepository parentRepository;
    StudentRepository studentRepository;

    ParentMapper parentMapper = ParentMapper.MAPPER;

    @Override
    public ParentResponseDto addParent(ParentRequestDto parentRequestDto) {
        Parent newParent = parentMapper.toEntity(parentRequestDto);

        newParent.setChildren(studentRepository.findAllById(parentRequestDto.childrenId()));

        return parentMapper.toResponseDto(parentRepository.save(newParent));
    }

    @Override
    public Page<ParentResponseDto> getAllParents(Pageable pageable) {
        Page<Parent> parents = parentRepository.findAll(pageable);

        return parents.map(parentMapper::toResponseDto);
    }

    @Override
    public ParentResponseDto getParentById(Long id) {
        return parentMapper.toResponseDto(getExistingParentById(id));
    }

    @Override
    public ParentResponseDto updateParentById(Long id, ParentRequestDto parentRequestDto) {
        Parent existingParent = getExistingParentById(id);

        parentMapper.updateEntity(parentRequestDto, existingParent);
        parentRepository.save(existingParent);

        return parentMapper.toResponseDto(existingParent);
    }

    @Override
    public void deleteParentById(Long id) {
        parentRepository.findById(id).ifPresent(parentRepository::delete);
    }

    private Parent getExistingParentById(Long id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent not found"));
    }
}
