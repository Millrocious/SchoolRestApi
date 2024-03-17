package com.endropioz.schoolrestapp.csvutil.validation.validator;

import com.endropioz.schoolrestapp.csvutil.validation.ValidationResult;
import com.endropioz.schoolrestapp.student.dto.StudentRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class StudentValidator implements Validator<StudentRequestDto> {
    private final jakarta.validation.Validator beanValidator;

    public StudentValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        beanValidator = factory.getValidator();
    }

    @Override
    public void validate(StudentRequestDto student, Integer rowNumber, ValidationResult result) {
        Set<ConstraintViolation<StudentRequestDto>> violations = beanValidator.validate(student);

        for (ConstraintViolation<StudentRequestDto> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            result.addErrorDetail(rowNumber, propertyPath, message);
        }
    }
}