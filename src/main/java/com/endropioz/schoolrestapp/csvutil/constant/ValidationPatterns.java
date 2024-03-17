package com.endropioz.schoolrestapp.csvutil.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationPatterns {
    public static final String INVALID_CHARS_PATTERN_NAME = "[^A-Za-z'\\-]+";
    public static final String VALIDATION_PATTERN_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String INVALID_CHARS_PATTERN_EMAIL = "[^A-Za-z0-9._%+-@]";
    public static final String INVALID_CHARS_PATTERN_PHONE = "[^0-9\\-() +]+";
    public static final String INVALID_CHARS_PATTERN_ADDRESS = "[^A-Za-z0-9/.,\\- ']+";
}
