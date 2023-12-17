package com.jstar.phone.exception;

public class PhoneNotAvailableException extends RuntimeException {
    public PhoneNotAvailableException(String message) {
        super(message);
    }
}