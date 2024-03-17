package com.endropioz.schoolrestapp.student.dto;

import com.poiji.annotation.ExcelCellName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequestDto {
        @NotBlank(message = "First name is required")
        @ExcelCellName("firstName")
        String firstName;

        @NotBlank(message = "Last name is required")
        @ExcelCellName("lastName")
        String lastName;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        @ExcelCellName("email")
        String email;

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\+?[0-9]+$", message = "Phone must contain only digits and an optional plus symbol")
        @ExcelCellName("phone")
        String phone;
}
