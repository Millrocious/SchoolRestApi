package com.endropioz.schoolrestapp.csvutil.validation.validator;


import com.endropioz.schoolrestapp.csvutil.model.Person;
import com.endropioz.schoolrestapp.csvutil.validation.ValidationResult;
import com.endropioz.schoolrestapp.csvutil.validation.ValidationUtil;

import static com.endropioz.schoolrestapp.csvutil.constant.ValidationPatterns.*;

public class PersonValidator implements Validator<Person> {
    @Override
    public void validate(Person entity, Integer rowNumber, ValidationResult result) {

        String firstName = ValidationUtil.trimField(entity.getFirstname());
        ValidationUtil.validateField(firstName, INVALID_CHARS_PATTERN_NAME, "firstname", rowNumber, result);

        String lastName = ValidationUtil.trimField(entity.getLastname());
        ValidationUtil.validateField(lastName, INVALID_CHARS_PATTERN_NAME, "lastname", rowNumber, result);

        String phone = ValidationUtil.trimField(entity.getPhone());
        ValidationUtil.validateField(phone, INVALID_CHARS_PATTERN_PHONE, "phone", rowNumber, result);

        String email = ValidationUtil.trimField(entity.getEmail());
        ValidationUtil.validateField(email, INVALID_CHARS_PATTERN_EMAIL, "email", rowNumber, result);
        ValidationUtil.validateEmailStructure(email, rowNumber, result);

        String address = ValidationUtil.trimField(entity.getAddress());
        ValidationUtil.validateField(address, INVALID_CHARS_PATTERN_ADDRESS, "address", rowNumber, result);
    }
}