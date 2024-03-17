package com.endropioz.schoolrestapp.core.exception.handler;

public class UnauthorizedAccessException extends Throwable {
    public UnauthorizedAccessException(String massage) {
        super(massage);
    }
}
