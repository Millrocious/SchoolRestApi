package com.endropioz.schoolrestapp.csvutil.validation.validator;

import com.endropioz.schoolrestapp.csvutil.validation.ValidationResult;
import com.endropioz.schoolrestapp.csvutil.validation.ValidationUtil;
import com.endropioz.schoolrestapp.subject.dto.SubjectRequestDto;

public class SubjectValidator implements Validator<SubjectRequestDto> {
    private static final String INVALID_SUBJECT_PATTERN_NAME = "^[a-zа-я].*";

    @Override
    public void validate(SubjectRequestDto entity, Integer rowNumber, ValidationResult result) {
        String subjectName = ValidationUtil.trimField(entity.name());
        ValidationUtil.validateField(subjectName, INVALID_SUBJECT_PATTERN_NAME, "name", rowNumber, result);
    }
}
