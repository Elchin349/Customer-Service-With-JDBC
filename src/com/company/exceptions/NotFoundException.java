package com.company.exceptions;

public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException(String message) {
        this.message = message;
    }

    public NotFoundException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
