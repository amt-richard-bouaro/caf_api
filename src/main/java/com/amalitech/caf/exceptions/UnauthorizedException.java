package com.amalitech.caf.exceptions;

public class UnauthorizedException extends Exception {
    
    private final String message;
    
    public UnauthorizedException(String message) {
        this.message = message;
    }
    
    public UnauthorizedException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
        this.message = message;
        
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    
}
