package com.endropioz.schoolrestapp.csvutil.validation;

import com.endropioz.schoolrestapp.csvutil.excepion.ModelValidationException;
import com.endropioz.schoolrestapp.csvutil.validation.validator.Validator;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.endropioz.schoolrestapp.csvutil.constant.ValidationPatterns.VALIDATION_PATTERN_EMAIL;

@Slf4j
@UtilityClass
public class ValidationUtil {
    public <T> List<T> validateData(List<T> entities, Validator<T> validator) {
        ValidationResult validationResult = new ValidationResult();

        for (int i = 0; i < entities.size(); i++) {
            T entity = entities.get(i);
            validator.validate(entity, i + 1, validationResult);
        }

        if (validationResult.getErrorsFieldsCount() > 0) {
            log.info("Validation error(s) occurred!");
            throw new ModelValidationException(validationResult);
        }

        return entities;
    }

    public void validateField(String value, String antipattern, String fieldName, Integer rowNumber, ValidationResult result) {
        if (StringUtil.isNotBlank(value)) {
            Pattern invalidPattern = Pattern.compile(antipattern);
            Matcher invalidMatcher = invalidPattern.matcher(value);

            Set<Character> invalidChars = new HashSet<>();
            while (invalidMatcher.find()) {
                invalidChars.add(value.charAt(invalidMatcher.start()));
            }

            if (!invalidChars.isEmpty()) {
                result.addInvalidCharacters(rowNumber, fieldName, invalidChars);
                result.addErrorDetail(rowNumber, fieldName, String.format("Invalid field value: %s", value));
            }
        }
    }

    public void validateEmailStructure(String email, Integer rowNumber, ValidationResult result) {
        Pattern structurePattern = Pattern.compile(VALIDATION_PATTERN_EMAIL);
        if (!structurePattern.matcher(email).matches()) {
            result.addErrorDetail(rowNumber, "email", "Email structure is invalid: " + email);
        }
    }

    public String trimField(String field) {
        return StringUtil.isNotBlank(field) ? field.trim() : null;
    }
}