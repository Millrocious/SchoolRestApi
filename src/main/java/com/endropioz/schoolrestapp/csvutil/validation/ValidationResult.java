package com.endropioz.schoolrestapp.csvutil.validation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.*;

@NoArgsConstructor
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationResult {
    final String errorMessage = "Validation failed";
    int errorsFieldsCount = 0;
    final Map<Integer, Map<String, Set<Character>>> invalidCharacters = new HashMap<>();
    final Map<Integer, Map<String, List<String>>> errorDetails = new HashMap<>();

    public void addInvalidCharacters(Integer rowNumber, String fieldName, Set<Character> invalidChars) {
        invalidCharacters.computeIfAbsent(rowNumber, k -> new HashMap<>())
                .computeIfAbsent(fieldName, k -> new HashSet<>())
                .addAll(invalidChars);

        incrementErrorFieldsCount();
    }

    public void addErrorDetail(Integer rowNumber, String fieldName, String errorDetail) {
        errorDetails.computeIfAbsent(rowNumber, k -> new HashMap<>())
                .computeIfAbsent(fieldName, k -> new ArrayList<>())
                .add(errorDetail);

        // Increment errorsFieldsCount only if there are no previous invalid characters recorded for this field.
        // This ensures that the count of fields with errors isn't inflated by multiple validations for the same field.
        boolean isInvalidCharsAbsent = !invalidCharacters.getOrDefault(rowNumber, Collections.emptyMap()).containsKey(fieldName);

        if (isInvalidCharsAbsent) {
            incrementErrorFieldsCount();
        }
    }

    public void incrementErrorFieldsCount() {
        errorsFieldsCount++;
    }
}