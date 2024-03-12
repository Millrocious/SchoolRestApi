package com.endropioz.schoolrestapp.classgroup.dto;

import jakarta.validation.constraints.Pattern;

public record ClassGroupRequestDto(
        @Pattern(
                regexp = "\\d+-[А-ЯA-Z]",
                message = "The class name must be in the format of 'number-letter' (e.g., '10-A')"
        )
        String name
) {
}
