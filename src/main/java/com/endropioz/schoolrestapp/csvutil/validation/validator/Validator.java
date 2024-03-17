package com.endropioz.schoolrestapp.csvutil.validation.validator;

import com.endropioz.schoolrestapp.csvutil.validation.ValidationResult;

public interface Validator<T> {
    void validate(T entity, Integer rowNumber, ValidationResult result);
}
