package com.endropioz.schoolrestapp.csvutil.excepion;

import com.endropioz.schoolrestapp.csvutil.validation.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ModelValidationException extends RuntimeException {
    private final ValidationResult validationResult;
}