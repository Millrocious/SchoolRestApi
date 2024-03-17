package com.endropioz.schoolrestapp.csvutil.model;

import com.poiji.annotation.ExcelCellName;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    @ExcelCellName("firstname")
    String firstname;

    @ExcelCellName("lastname")
    String lastname;

    @ExcelCellName("phone")
    String phone;

    @ExcelCellName("email")
    String email;

    @ExcelCellName("address")
    String address;
}
