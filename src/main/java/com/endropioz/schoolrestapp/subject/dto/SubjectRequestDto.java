package com.endropioz.schoolrestapp.subject.dto;

import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequestDto {
        @ExcelCellName("name")
        @CsvBindByName(column = "title", required = true)
        @NotBlank(message = "The field cannot be blank")
        String name;
}
