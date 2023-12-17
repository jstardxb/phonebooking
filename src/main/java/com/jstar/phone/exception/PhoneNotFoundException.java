package com.jstar.phone.exception;

public class PhoneNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Phone not found!";

    private PhoneNotFoundException(String message) {
        super(message);
    }

    public static PhoneNotFoundException instance() {
        return new PhoneNotFoundException(DEFAULT_MESSAGE);
    }
}