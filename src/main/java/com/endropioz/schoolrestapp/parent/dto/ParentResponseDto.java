package com.endropioz.schoolrestapp.parent.dto;

import java.util.List;

public record ParentResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        List<Long> childrenId
) {
}
