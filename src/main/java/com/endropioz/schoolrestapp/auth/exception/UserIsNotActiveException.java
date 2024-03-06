package com.endropioz.schoolrestapp.auth.exception;

public class UserIsNotActiveException extends RuntimeException {
    public UserIsNotActiveException(String message) {
        super(message);
    }
}
