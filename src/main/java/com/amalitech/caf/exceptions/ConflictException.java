package com.amalitech.caf.exceptions;

public class ConflictException extends RuntimeException {
    private final String message;

    public ConflictException(String message) {
        this.message = message;
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
