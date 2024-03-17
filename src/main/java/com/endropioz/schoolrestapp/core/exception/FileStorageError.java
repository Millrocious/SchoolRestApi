package com.endropioz.schoolrestapp.core.exception;

public class FileStorageError extends RuntimeException {
    public FileStorageError(String message) {
        super(message);
    }

    public FileStorageError(String message, Throwable cause) {
        super(message, cause);
    }
}
